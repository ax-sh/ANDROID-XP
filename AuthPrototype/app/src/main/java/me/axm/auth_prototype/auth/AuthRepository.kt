package me.axm.auth_prototype.auth

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import me.axm.auth_prototype.SessionManager
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
            Timber.tag("authenticate").i("gooooo")
            return try {
                val token = session.fetchAuthToken() ?: return AuthResult.Unauthorized()
                Timber.tag("auth token repo").d("token $token")
                return AuthResult.UnknownError()
            } catch (e: Exception) {
                AuthResult.UnknownError()
            }
        }
    }
