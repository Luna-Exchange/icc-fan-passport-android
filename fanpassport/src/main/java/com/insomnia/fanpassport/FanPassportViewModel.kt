package com.insomnia.fanpassport

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.insomnia.fanpassport.services.FanPassportApiClient
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FanPassportViewModel : ViewModel() {

    private val mToken  = MutableStateFlow<Result>(Result.Default)

    val token : StateFlow<Result>
        get() = mToken

    fun encodeUser(user: User?, baseUrl : String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (user == null) return@launch
                val service = FanPassportApiClient.create(baseUrl)
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

    object Default : Result()

}
@Parcelize
data class User(val authToken: String, val name: String, val email: String) : Parcelable

@Parcelize
data class SdkParam(var user: User? = null, var entryPoint : String = "", var publicKey : String= "", var accountId : String = "", var environment: Environment = Environment.DEVELOPMENT, var action : SdkActions = SdkActions.DEFAULT) : Parcelable
