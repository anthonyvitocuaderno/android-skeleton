package com.vitocuaderno.skeleton.features.main.todos

import androidx.recyclerview.widget.RecyclerView
import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.databinding.ItemTaskBinding

class TaskViewHolder(
    private val binding: ItemTaskBinding,
    private val listener: TaskViewHolderListenr
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task) {
        binding.task = task
        binding.executePendingBindings()

        binding.complete.setOnCheckedChangeListener { _, isChecked ->
            listener.onToggle(task, isChecked)
        }

        binding.title.setOnClickListener {
            listener.onClick(task)
        }
    }
}

interface TaskViewHolderListenr {
    fun onClick(task: Task)
    fun onToggle(task: Task, newStatus: Boolean)
}