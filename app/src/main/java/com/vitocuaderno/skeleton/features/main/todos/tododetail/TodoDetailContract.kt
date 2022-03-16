package com.vitocuaderno.skeleton.features.main.todos.tododetail

import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.features.common.BasePresenter
import com.vitocuaderno.skeleton.features.common.BaseView

class TodoDetailContract {
    interface View : BaseView<Presenter> {
        fun finish()
        fun bind(task: Task)
    }

    interface Presenter : BasePresenter<View> {
        suspend fun loadTask(id: String)
        fun edit(title: String, description: String, completed: Boolean)
        fun delete()
    }
}