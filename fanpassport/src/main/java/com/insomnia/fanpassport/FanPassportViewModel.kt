package com.insomnia.fanpassport

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.insomnia.fanpassport.services.FanPassportApiClient
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FanPassportViewModel : ViewModel() {

    private val mToken  = MutableSharedFlow<Result>()

    val token : SharedFlow<Result>
        get() = mToken

    fun encodeUser(user: User?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (user == null) return@launch
                val service = FanPassportApiClient.create()
                val tokenFlow = flow {
                    emit(service.encode(user))
                }
                    .catch {
                        mToken.emit(Result.Failed(it.message ?: ""))
                    }
                tokenFlow.collect {
                    mToken.emit(Result.Success(it.data.token))
                }
            } catch (e : Exception) {

            }
        }
    }
}


sealed class Result {
    data class Success(val token : String) : Result()
    data class Failed(val message : String) : Result()

}
@Parcelize
data class User(val authToken: String, val name: String, val email: String) :
    Parcelable