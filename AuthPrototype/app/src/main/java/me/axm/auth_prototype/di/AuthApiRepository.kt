package me.axm.auth_prototype.di

import me.axm.auth_prototype.auth.AuthResult
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApiRepository {
//    @GET("authenticate")
//    suspend fun user(@Header("Authorization") token: String): String
//
//    suspend fun authenticate(): AuthResult<Unit>

    //    https://zenquotes.io/api/today
    @GET("today")
    suspend fun getQuoteOfTheDay(): String
}

