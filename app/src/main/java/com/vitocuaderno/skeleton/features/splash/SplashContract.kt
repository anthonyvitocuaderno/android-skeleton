package com.vitocuaderno.skeleton.features.splash

import com.vitocuaderno.skeleton.features.common.BasePresenter
import com.vitocuaderno.skeleton.features.common.BaseView

class SplashContract {

    interface View : BaseView<Presenter> {

        fun navigateToOnboarding()

        fun navigateToMain()
    }

    interface Presenter : BasePresenter<View> {
        suspend fun checkSession()
    }
}
