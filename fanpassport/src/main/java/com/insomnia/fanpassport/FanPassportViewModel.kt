package com.insomnia.fanpassport

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.insomnia.fanpassport.services.FanPassportApiClient
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FanPassportViewModel : ViewModel() {

    private val mToken  = MutableSharedFlow<String>()

    val token : SharedFlow<String>
        get() = mToken

    fun encodeUser(user: User?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (user == null) return@launch
            val service = FanPassportApiClient.create()
            val tokenFlow = flow{
                emit(service.encode(user))
            }
            tokenFlow.collect {
                mToken.emit(it.token)
            }
        }
    }
}


@Parcelize
data class User(val authToken: String, val name: String, val email: String, val username: String) :
    Parcelable