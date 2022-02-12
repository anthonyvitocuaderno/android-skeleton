package com.vitocuaderno.skeleton.features.splash

sealed class SplashState {
    object Initializing : SplashState()
    object IsLoggedIn : SplashState()
    object NotLoggedIn : SplashState()
}
