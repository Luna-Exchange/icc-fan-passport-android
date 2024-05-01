package com.insomnia.fanpassport.services

import com.google.gson.annotations.SerializedName
import com.insomnia.fanpassport.User
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

interface FanPassportAPIService {
    @POST("/auth/encode")
    suspend fun encode(@Body user: User): AuthResponse

}

data class AuthResponse(
    @SerializedName("statusCode")
    val statusCode: Long,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Data,
    val metadata: Any?,
    val error: Any?,
)

data class Data(
    @SerializedName("token")
    val token: String,
)