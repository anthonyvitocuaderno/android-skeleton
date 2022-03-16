package com.vitocuaderno.skeleton.features.main.todos

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.data.local.models.Task
import com.vitocuaderno.skeleton.databinding.FragmentTodoListBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import com.vitocuaderno.skeleton.features.main.MainFragmentDirections
import com.vitocuaderno.skeleton.features.main.todos.tododetail.TodoDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TodoListFragment : BaseFragment<FragmentTodoListBinding>(), TodoListContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_todo_list

    @Inject
    lateinit var presenter: TodoListPresenter

    override val title = "ToDo"

    private var adapter: TodoListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.setView(this)

        binding.apply {

            lifecycleOwner = viewLifecycleOwner

            fabClean.setOnClickListener {
                presenter.clearCompletedTasks()
                reload()
            }

            fabAdd.setOnClickListener {
                presenter.addNewTask()
            }

            adapter = TodoListAdapter(
                object : TaskViewHolderListenr {
                    override fun onClick(task: Task) {

                        setFragmentResultListener(
                            TodoDetailFragment.REQUEST_UPDATE_TASK
                        ) { _, _ ->
                            reload()
                        }

                        findNavController().navigate(
                            MainFragmentDirections.actionMainFragmentToTodoDetailFragment(task.id)
                        )
                    }

                    override fun onToggle(task: Task, newStatus: Boolean) {
                        if (newStatus) {
                            presenter.completeTask(task)
                        } else {
                            presenter.undoTask(task)
                        }
                        reload()
                    }
                }
            )
            recycler.adapter = adapter

            reload()
        }
    }

    private fun reload() {
        lifecycleScope.launch {
            presenter.loadTasks()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.start()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsetView()
    }

    override fun setLoadingIndicator(active: Boolean) {
        // TODO
    }

    override fun showTasks(tasks: List<Task>) {
        adapter?.submitData(tasks)
    }

    override fun showAddTask() {

        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToAddTaskFragment()
        )
    }
}
