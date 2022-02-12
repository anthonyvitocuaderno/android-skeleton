package com.vitocuaderno.skeleton.features.splash

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.vitocuaderno.skeleton.features.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val handle: SavedStateHandle
) : BaseViewModel() {

    private val _state = MutableLiveData<SplashState>(SplashState.Initializing)
    val state: LiveData<SplashState> = _state

    override fun isFirstTimeUiCreate(bundle: Bundle?) {
        // TODO
    }
}
