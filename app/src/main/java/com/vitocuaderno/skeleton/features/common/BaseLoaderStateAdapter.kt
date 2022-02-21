package com.vitocuaderno.skeleton.features.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vitocuaderno.skeleton.databinding.RowLoaderBinding

class BaseLoaderStateAdapter(
    private val retry: () -> Unit
) :
    LoadStateAdapter<BaseLoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(
            RowLoaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry
        )
    }

    class LoaderViewHolder(private val binding: RowLoaderBinding, retry: () -> Unit) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun getInstance(parent: ViewGroup, retry: () -> Unit): LoaderViewHolder {
                return LoaderViewHolder(
                    RowLoaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    retry
                )
            }
        }

        init {
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Loading) {
                binding.mlLoader.transitionToEnd()
            } else {
                binding.mlLoader.transitionToStart()
            }
        }
    }
}
