package com.vitocuaderno.skeleton.data.repository.task

import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository
) : TaskRepository {

    private val list = mutableListOf(
        Task(authRepository.getSession()!!.username, "Breakfast", "Cereal + Coffee"),
        Task(authRepository.getSession()!!.username, "Lunch", "Fish and Rice"),
        Task(authRepository.getSession()!!.username, "Dinner", "Pork and Rice")
    )

    override suspend fun getTasks(): List<Task> {
        return list
    }

    override suspend fun getTask(id: String): Task {
        return list.first { it.id == id }
    }

    override fun saveTask(title: String, description: String) {
        val task = Task(
            username = authRepository.getSession()!!.username,
            title = title,
            description = description
        )

        list.add(task)
    }

    override fun updateTask(
        taskId: String,
        title: String,
        description: String,
        completed: Boolean
    ) {
        list.first {
            it.id == taskId
        }.apply {
            this.completed = completed
            this.title = title
            this.description = description
        }
    }

    override fun completeTask(taskId: String) {
        list.first {
            it.id == taskId
        }.completed = true
    }

    override fun undoTask(taskId: String) {
        list.first {
            it.id == taskId
        }?.completed = false
    }

    override fun clearCompletedTasks() {
        list.removeIf {
            it.completed
        }
    }

    override fun deleteTask(taskId: String) {
        list.removeIf {
            it.id == taskId
        }
    }
}
