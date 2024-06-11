package me.axm.auth_prototype.di

import me.axm.auth_prototype.AuthApi
import me.axm.auth_prototype.auth.AuthResult
import retrofit2.HttpException

class AuthApiRepositoryImpl(private val api: AuthApi) : AuthApiRepository {
    override suspend fun user(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun getQuoteOfTheDay(): String {
        TODO("Not yet implemented")
    }

    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            api.authenticate("Bearer")
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}
