package com.vitocuaderno.skeleton.features.main

sealed class MainState {
    object Idle : MainState()
    object Busy : MainState()
    object IsLoggedOut : MainState()
}
