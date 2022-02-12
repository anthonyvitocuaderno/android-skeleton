package com.vitocuaderno.skeleton.features.splash

sealed class SplashState {
    object Initializing : SplashState()
    data class IsLoggedIn(val value: Boolean) : SplashState()

    override fun toString(): String {
        return when (this) {
            is Initializing -> {
                "Initializing..."
            } else -> {
                super.toString()
            }
        }
    }
}
