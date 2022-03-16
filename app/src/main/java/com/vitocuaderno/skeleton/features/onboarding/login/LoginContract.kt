package com.vitocuaderno.skeleton.features.onboarding.login

import com.vitocuaderno.skeleton.features.common.BasePresenter
import com.vitocuaderno.skeleton.features.common.BaseView

class LoginContract {
    interface View : BaseView<Presenter> {
        fun resetToIdle(username: String?)
        fun showBusy()
        fun showSuccess()
        fun showFailed(message: String)
    }

    interface Presenter : BasePresenter<View> {
        suspend fun login(username: String, password: String)
    }
}
