package com.vitocuaderno.skeleton.features.main.dummy

sealed class DummyState {
    object CheckingSession : DummyState()
    data class Welcome(val msg: String) : DummyState()

    override fun toString(): String {
        return when (this) {
            is CheckingSession -> {
                "Checking session..."
            } else -> {
                super.toString()
            }
        }
    }
}
