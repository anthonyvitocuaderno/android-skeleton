package com.vitocuaderno.skeleton.features.main.todos.tododetail

import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.data.repository.task.TaskRepository
import javax.inject.Inject

class TodoDetailPresenter @Inject constructor(
    private val taskRepository: TaskRepository
) : TodoDetailContract.Presenter {

    private var mView: TodoDetailContract.View? = null

    lateinit var task: Task

    override fun edit(title: String, description: String, completed: Boolean) {
        taskRepository.updateTask(task.id, title, description, completed)
        mView?.finish()
    }

    override fun delete() {
        taskRepository.deleteTask(task.id)
        mView?.finish()
    }

    override fun start() {
    }

    override suspend fun loadTask(id: String) {
        task = taskRepository.getTask(id)
        mView?.bind(task)
    }

    override fun setView(view: TodoDetailContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }
}
