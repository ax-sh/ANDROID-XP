package me.axm.auth_prototype.di

import retrofit2.http.GET

interface AuthApiRepository {
//    @GET("authenticate")
//    suspend fun user(@Header("Authorization") token: String): String
//
//    suspend fun authenticate(): AuthResult<Unit>

    //    https://zenquotes.io/api/today
    @GET("today")
    suspend fun getQuoteOfTheDay(): String
}
