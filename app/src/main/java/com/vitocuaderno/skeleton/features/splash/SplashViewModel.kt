package com.vitocuaderno.skeleton.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import com.vitocuaderno.skeleton.features.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<SplashState>(SplashState.Initializing)
    val state: LiveData<SplashState> = _state

    override fun start() {
        super.start()
        viewModelScope.launch {
            delay(500)
            _state.postValue(SplashState.IsLoggedIn(authRepository.getSession() != null))
        }
    }
}
