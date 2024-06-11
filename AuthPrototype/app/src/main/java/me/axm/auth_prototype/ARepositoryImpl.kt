package me.axm.auth_prototype

import android.content.SharedPreferences
import me.axm.auth_prototype.auth.AuthResult
import retrofit2.HttpException

class ARepositoryImpl(
    private val api: AuthApi
) : ARepository {
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