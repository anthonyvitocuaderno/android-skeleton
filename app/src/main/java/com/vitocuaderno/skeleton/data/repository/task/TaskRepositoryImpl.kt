package com.vitocuaderno.skeleton.data.repository.task

import com.vitocuaderno.skeleton.data.local.TaskJsonDatabase
import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val taskJsonDatabase: TaskJsonDatabase
) : TaskRepository {

    private val list: MutableList<Task> = mutableListOf()

    init {
        list.addAll(taskJsonDatabase.read())
    }

    // TODO file stream and cache

    override suspend fun getTasks(): List<Task> {
        return list.filter {
            it.username == authRepository.getSession()!!.username
        }
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
        write()
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
        write()
    }

    override fun completeTask(taskId: String) {
        list.first {
            it.id == taskId
        }.completed = true
        write()
    }

    override fun undoTask(taskId: String) {
        list.first {
            it.id == taskId
        }?.completed = false
        write()
    }

    override fun clearCompletedTasks() {
        list.removeIf {
            it.completed
        }
        write()
    }

    override fun deleteTask(taskId: String) {
        list.removeIf {
            it.id == taskId
        }
        write()
    }

    private fun write() {
        taskJsonDatabase.write(list)
    }
}
