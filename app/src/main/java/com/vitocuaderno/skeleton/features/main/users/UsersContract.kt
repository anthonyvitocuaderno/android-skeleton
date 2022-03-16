package com.vitocuaderno.skeleton.features.main.users

import androidx.paging.PagingData
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.features.common.BasePresenter
import com.vitocuaderno.skeleton.features.common.BaseView

class UsersContract {
    interface View : BaseView<Presenter> {
        fun resetToIdle()
        fun showBusy()
        fun showEmptyList()
    }

    interface Presenter : BasePresenter<View> {
        // TODO
    }
}