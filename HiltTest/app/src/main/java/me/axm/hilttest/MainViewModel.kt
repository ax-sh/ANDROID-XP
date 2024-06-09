package me.axm.hilttest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// https://github.com/philipplackner/DaggerHiltCourse/blob/master/app/src/main/java/com/plcoding/daggerhiltcourse/di/RepositoryModule.kt
// @HiltViewModel will make models to be
// created using Hilt's model factory
// @Inject annotation used to inject all
// dependencies to view model class
@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val repository: AuthRepository,
    ) : ViewModel() {
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//

        private val isAuthenticated = MutableLiveData<Boolean>()
        private val token = MutableLiveData<String>()
        val isAuthenticatedLiveData: LiveData<Boolean> = isAuthenticated

        init {
            isAuthenticated.value = false
            checkAuth()
        }

        private fun checkAuth() {
            val accessToken = repository.getToken()
            println(accessToken + " dsfffffff")
            token.value = accessToken
            isAuthenticated.value = accessToken != null
        }
    }
