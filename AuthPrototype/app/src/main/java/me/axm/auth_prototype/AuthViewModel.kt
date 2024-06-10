package me.axm.auth_prototype

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import net.openid.appauth.AuthorizationService
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject
constructor(
    private val session: SessionManager,
    @ApplicationContext private val appContext: Context,
) : ViewModel()  {
    private val authService: AuthorizationService = AuthorizationService(appContext)
    init {
        Timber.tag("fooooooo ").i("gooooo")
    }

}
