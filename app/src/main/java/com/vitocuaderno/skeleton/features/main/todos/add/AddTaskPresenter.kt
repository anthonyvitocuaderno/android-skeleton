package com.vitocuaderno.skeleton.features.main.todos.add

import com.vitocuaderno.skeleton.data.repository.task.TaskRepository
import javax.inject.Inject

class AddTaskPresenter @Inject constructor(
    private val taskRepository: TaskRepository
) : AddTaskContract.Presenter {

    private var mView: AddTaskContract.View? = null

    override fun submitTask(title: String, description: String) {

        taskRepository.saveTask(title, description)

        mView?.showSuccess()
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun setView(view: AddTaskContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }
}
