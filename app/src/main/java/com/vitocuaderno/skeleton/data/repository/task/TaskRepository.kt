package com.vitocuaderno.skeleton.data.repository.task

import com.vitocuaderno.skeleton.data.local.models.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun getTask(id: String): Task
    fun saveTask(title: String, description: String)
    fun updateTask(taskId: String, title: String, description: String, completed: Boolean)
    fun completeTask(taskId: String)
    fun undoTask(taskId: String)
    fun clearCompletedTasks()
    fun deleteTask(taskId: String)
}
