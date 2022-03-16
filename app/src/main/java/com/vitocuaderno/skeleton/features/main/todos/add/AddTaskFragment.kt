package com.vitocuaderno.skeleton.features.main.todos.tododetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentAddTaskBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import com.vitocuaderno.skeleton.features.main.todos.add.AddTaskContract
import com.vitocuaderno.skeleton.features.main.todos.add.AddTaskPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddTaskFragment : BaseFragment<FragmentAddTaskBinding>(), AddTaskContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_add_task

    override val title = "New Task"

    @Inject
    lateinit var presenter: AddTaskPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setView(this)

        binding.btnSubmit.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            presenter.submitTask(title, description)
        }
    }

    override fun showSuccess() {
        findNavController().navigateUp()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsetView()
    }

    companion object {
        val REQUEST_ADD_TASK = "REQUEST_ADD_TASK"
    }
}
