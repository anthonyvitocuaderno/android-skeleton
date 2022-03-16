package com.vitocuaderno.skeleton.features.main.todos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.databinding.ItemTaskBinding

class TodoListAdapter(
    private val taskViewHolderListener: TaskViewHolderListenr
) : RecyclerView.Adapter<TaskViewHolder>() {

    private var tasks: List<Task> = emptyList()

    fun submitData(list: List<Task>) {
        tasks = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        tasks.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            taskViewHolderListener
        )
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}
