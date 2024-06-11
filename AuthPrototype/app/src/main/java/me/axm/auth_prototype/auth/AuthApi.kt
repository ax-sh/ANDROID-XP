package me.axm.auth_prototype.auth

import retrofit2.http.GET
import retrofit2.http.Header

interface AuthApi {
    //    @POST("signup")
//    suspend fun signUp(
//        @Body request: AuthRequest
//    )
//
//    @POST("signin")
//    suspend fun signIn(
//        @Body request: AuthRequest
//    ): TokenResponse

    @GET("authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String,
    )
}
