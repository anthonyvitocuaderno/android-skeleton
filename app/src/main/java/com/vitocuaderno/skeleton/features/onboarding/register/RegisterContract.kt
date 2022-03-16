package com.vitocuaderno.skeleton.features.onboarding.register

import com.vitocuaderno.skeleton.features.common.BasePresenter
import com.vitocuaderno.skeleton.features.common.BaseView

class RegisterContract {
    interface View : BaseView<Presenter> {
        fun resetToIdle(username: String?)
        fun showBusy()
        fun showSuccess()
        fun showFailed(message: String)
    }

    interface Presenter : BasePresenter<View> {
        suspend fun register(username: String, password: String, confirmPassword: String)
    }
}