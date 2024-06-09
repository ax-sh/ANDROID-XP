package me.axm.hilttest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// @HiltViewModel will make models to be
// created using Hilt's model factory
// @Inject annotation used to inject all
// dependencies to view model class
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Lazy<AuthRepository>
): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isAuthenticated = MutableLiveData<Boolean>()

    val isAuthenticated= MutableLiveData<Boolean>()
    init {
        checkAuth()
        repository.get()
    }

    private fun checkAuth() {
        _isLoading.value = true
        _isAuthenticated.value = true
        _isLoading.value = false
    }
}
