package com.vitocuaderno.skeleton.features.main.todos.tododetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.databinding.FragmentTodoDetailBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TodoDetailFragment : BaseFragment<FragmentTodoDetailBinding>(), TodoDetailContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_todo_detail

    override val title = "Edit Task"

    @Inject
    lateinit var presenter: TodoDetailPresenter

    val args: TodoDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setView(this)

        binding.apply {
            fabDelete.setOnClickListener {
                presenter.delete()
            }

            fabSave.setOnClickListener {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                val completed = checkbox.isChecked
                presenter.edit(title, description, completed)
                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            presenter.loadTask(args.taskId)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsetView()
    }

    override fun finish() {
        requireParentFragment().setFragmentResult(REQUEST_UPDATE_TASK, bundleOf())
        findNavController().navigateUp()
    }

    override fun bind(task: Task) {
        binding.apply {
            etTitle.setText(task.title)
            etDescription.setText(task.description)
            checkbox.isChecked = task.completed
        }
    }

    companion object {
        val REQUEST_UPDATE_TASK = "REQUEST_UPDATE_TASK"
    }
}
