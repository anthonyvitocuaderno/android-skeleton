package com.vitocuaderno.skeleton.features.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vitocuaderno.skeleton.features.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableLiveData<OnboardingState>(OnboardingState.Idle)
    val state: LiveData<OnboardingState> = _state

    override fun start() {
        super.start()
        // TODO
    }
}
