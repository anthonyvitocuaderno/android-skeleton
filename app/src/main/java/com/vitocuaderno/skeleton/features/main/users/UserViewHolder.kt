package com.vitocuaderno.skeleton.features.main.users

import androidx.recyclerview.widget.RecyclerView
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.databinding.RowUserBinding

class UserViewHolder(
    private val binding: RowUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.apply {
            tvEmail.text = user.firstName
            executePendingBindings()
        }
    }
}

