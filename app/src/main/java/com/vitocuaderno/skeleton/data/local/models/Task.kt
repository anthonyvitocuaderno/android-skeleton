package com.vitocuaderno.skeleton.data.local.models

import java.util.*

data class Task(
    var username: String,
    var title: String,
    var description: String,
    var completed: Boolean = false,
    val id: String = UUID.randomUUID().toString()
) {

    val titleForList: String?
        get() = title.ifBlank {
            description.ifBlank {
                "Blank"
            }
        }
    val isActive: Boolean
        get() = !completed
    val isEmpty: Boolean
        get() = title.isNullOrEmpty() &&
            description.isNullOrEmpty()

    override fun toString(): String {
        return "Task: $title"
    }
}
