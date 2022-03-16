package com.vitocuaderno.skeleton.features.main.todos

import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.data.repository.task.TaskRepository
import javax.inject.Inject

class TodoListPresenter @Inject constructor(
    private val taskRepository: TaskRepository
) : TodoListContract.Presenter {

    private var mView: TodoListContract.View? = null

    override fun result(requestCode: Int, resultCode: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun loadTasks() {
        mView?.setLoadingIndicator(true)
        val tasks = taskRepository.getTasks()

        mView?.showTasks(tasks)

        mView?.setLoadingIndicator(false)
    }

    override fun addNewTask() {
        mView?.showAddTask()
    }

    override fun openTaskDetails(requestedTask: Task) {
        TODO("Not yet implemented")
    }

    override fun completeTask(completedTask: Task) {
        taskRepository.completeTask(taskId = completedTask.id)
    }

    override fun undoTask(activeTask: Task) {
        taskRepository.undoTask(taskId = activeTask.id)
    }

    override fun clearCompletedTasks() {
        taskRepository.clearCompletedTasks()
    }

    override fun start() {
        // TODO
    }

    override fun setView(view: TodoListContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }
}
