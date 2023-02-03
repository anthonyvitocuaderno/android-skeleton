package com.vitocuaderno.skeleton.features.splash

sealed class SplashState {
    object Initializing : SplashState()
    data class IsLoggedIn(val value: Boolean) : SplashState()

    val msg: String get() {
        return when (this) {
            is Initializing -> {
                "Initializing..."
            }
            is IsLoggedIn -> {
                if (this.value) {
                    ""
                } else {
                    "Not logged in"
                }
            }
        }
    }
}
