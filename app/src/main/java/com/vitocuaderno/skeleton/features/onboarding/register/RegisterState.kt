package com.vitocuaderno.skeleton.features.onboarding.register

sealed class RegisterState {
    data class Idle(val username: String) : RegisterState()
    object Busy : RegisterState()
    object Success : RegisterState()
    data class Failed(val message: String) : RegisterState()
}
