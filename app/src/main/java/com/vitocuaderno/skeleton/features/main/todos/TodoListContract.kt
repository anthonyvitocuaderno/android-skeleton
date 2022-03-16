package com.vitocuaderno.skeleton.features.main.todos

import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.features.common.BasePresenter
import com.vitocuaderno.skeleton.features.common.BaseView

class TodoListContract {
    interface View : BaseView<Presenter> {

        fun setLoadingIndicator(active: Boolean)

        fun showTasks(tasks: List<Task>)

        fun showAddTask()
    }

    interface Presenter : BasePresenter<View> {
        fun result(requestCode: Int, resultCode: Int)

        suspend fun loadTasks()

        fun addNewTask()

        fun openTaskDetails(requestedTask: Task)

        fun completeTask(completedTask: Task)

        fun undoTask(activeTask: Task)

        fun clearCompletedTasks()
    }
}
