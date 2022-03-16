package com.vitocuaderno.skeleton.features.main.todos.add

import com.vitocuaderno.skeleton.features.common.BasePresenter
import com.vitocuaderno.skeleton.features.common.BaseView

class AddTaskContract {
    interface View : BaseView<Presenter> {
        fun showSuccess()
    }

    interface Presenter : BasePresenter<View> {
        fun submitTask(title: String, description: String)
    }
}