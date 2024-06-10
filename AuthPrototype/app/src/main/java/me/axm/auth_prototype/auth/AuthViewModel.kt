package me.axm.auth_prototype.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
    @Inject
    constructor(
        private val repository: AuthRepository,
    ) : ViewModel() {
         var state by mutableStateOf(AuthState())

        private val resultChannel = Channel<AuthResult<Unit>>()
        val authResults = resultChannel.receiveAsFlow()

        init {
            authenticate()
        }

        private fun authenticate() {
            Timber.tag("auth view model")
            viewModelScope.launch {
                state = state.copy(isLoading = true)
                val result = repository.authenticate()
                resultChannel.send(result)
                state = state.copy(isLoading = false)
            }
        }
    }
