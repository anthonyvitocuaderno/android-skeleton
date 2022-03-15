package com.vitocuaderno.skeleton.features.main

import com.vitocuaderno.skeleton.features.common.BasePresenter
import com.vitocuaderno.skeleton.features.common.BaseView

class MainContract {
    interface View : BaseView<Presenter> {
        fun resetToIdle()
        fun showBusy()
        fun showFailed(message: String)
        fun logout()
    }

    interface Presenter : BasePresenter<View> {
        fun logout()
    }
}
