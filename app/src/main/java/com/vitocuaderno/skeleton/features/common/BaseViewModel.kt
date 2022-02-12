package com.vitocuaderno.skeleton.features.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    @CallSuper
    open fun start() {
        // TODO
    }
}
