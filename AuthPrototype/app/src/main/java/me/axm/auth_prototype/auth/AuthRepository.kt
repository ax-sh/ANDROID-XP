package me.axm.auth_prototype.auth

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import me.axm.auth_prototype.di.AuthApiRepository
import net.openid.appauth.AuthorizationService
import timber.log.Timber
import javax.inject.Inject

class AuthRepository
    @Inject
    constructor(
        private val session: SessionManager,
        private val api: AuthApiRepository,
        @ApplicationContext private val appContext: Context,
    ) {
        private val authService: AuthorizationService = AuthorizationService(appContext)

        suspend fun authenticate(): AuthResult<Unit> {
            val logger = Timber.tag("AuthRepository")
            return try {
                val token = session.fetchAuthToken() ?: return AuthResult.Unauthorized()
                val response = api.getQuoteOfTheDay()
                logger.i("fuuuuu")

                Timber.tag("AuthRepository oauth auth token repo").d("token $token")
                return AuthResult.UnknownError()
            } catch (e: Exception) {
                AuthResult.UnknownError()
            }
        }
    }
