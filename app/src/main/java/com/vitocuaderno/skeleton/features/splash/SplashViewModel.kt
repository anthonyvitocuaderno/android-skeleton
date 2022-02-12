package com.vitocuaderno.skeleton.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.vitocuaderno.skeleton.features.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val handle: SavedStateHandle
) : BaseViewModel() {

    private val _state = MutableLiveData<SplashState>(SplashState.Initializing)
    val state: LiveData<SplashState> = _state

    override fun start() {
        super.start()
        viewModelScope.launch {
            delay(2000)
            _state.postValue(SplashState.IsLoggedIn)
        }
    }
}
