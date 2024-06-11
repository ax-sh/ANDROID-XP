package me.axm.auth_prototype.auth

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import net.openid.appauth.AuthorizationService
import timber.log.Timber
import javax.inject.Inject

class AuthRepository
    @Inject
    constructor(
        private val session: SessionManager,
        @ApplicationContext private val appContext: Context,
    ) {
        private val authService: AuthorizationService = AuthorizationService(appContext)

        suspend fun authenticate(): AuthResult<Unit> {
            return try {
                val token = session.fetchAuthToken() ?: return AuthResult.Unauthorized()
                Timber.tag("oauth auth token repo").d("token $token")
                return AuthResult.UnknownError()
            } catch (e: Exception) {
                AuthResult.UnknownError()
            }
        }
    }
