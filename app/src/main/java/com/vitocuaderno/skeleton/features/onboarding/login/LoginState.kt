package com.vitocuaderno.skeleton.features.onboarding.login

sealed class LoginState {
    data class Idle(val username: String) : LoginState()
    object Busy : LoginState()
    object Success : LoginState()
    data class Failed(val message: String) : LoginState()
}
