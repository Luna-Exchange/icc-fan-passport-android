package com.insomnia.fanpassport.services

import com.google.gson.annotations.SerializedName
import com.insomnia.fanpassport.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

interface FanPassportAPIService {
    @POST("/auth/encode")
    suspend fun encode(@Body user: User): TokenResponse

}

data class TokenResponse (
    @SerializedName("token")
    val token : String
)