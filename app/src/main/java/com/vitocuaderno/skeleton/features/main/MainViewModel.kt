package com.vitocuaderno.skeleton.features.main

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
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<MainState>(MainState.Idle)
    val state: LiveData<MainState> = _state

    fun logout() {
        viewModelScope.launch {
            _state.postValue(MainState.Busy)
            delay(100)
            authRepository.logoutAsync()
            _state.postValue(MainState.IsLoggedOut)
        }
    }
}
