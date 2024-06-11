package me.axm.auth_prototype.di

import me.axm.auth_prototype.auth.AuthApi

class AuthApiRepositoryImpl(private val api: AuthApi) : AuthApiRepository {
//    override suspend fun user(): String {
//        return
//    }

    override suspend fun getQuoteOfTheDay(): String {
        return api.getQuoteOfTheDay()
    }

//    override suspend fun authenticate(): AuthResult<Unit> {
//        Timber.tag("api").i("start")
//        return try {
//            val o = api.authenticate("Bearer")
//            Timber.tag("api").i(o.toString())
//            AuthResult.Authorized()
//        } catch (e: HttpException) {
//            if (e.code() == 401) {
//                AuthResult.Unauthorized()
//            } else {
//                AuthResult.UnknownError()
//            }
//        } catch (e: Exception) {
//            AuthResult.UnknownError()
//        }
//    }
}
