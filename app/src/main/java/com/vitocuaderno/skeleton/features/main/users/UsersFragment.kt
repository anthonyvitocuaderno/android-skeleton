package com.vitocuaderno.skeleton.features.main.users

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentUsersBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import com.vitocuaderno.skeleton.features.common.BaseLoaderStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_users

    override val viewModel: UsersViewModel by viewModels()

    override val title = "Users"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            lifecycleOwner = viewLifecycleOwner

            val adapter = UsersAdapter()
            val loaderStateAdapter = BaseLoaderStateAdapter { adapter.retry() }
            recycler.adapter = adapter.withLoadStateFooter(loaderStateAdapter)
            recycler.setEmptyView(emptyView)
            swipeRefresh.setOnRefreshListener {
                adapter.refresh()
            }
            adapter.addLoadStateListener {
                if (it.refresh is LoadState.Loading) {
                    swipeRefresh.isRefreshing = true
                    emptyView.visibility = View.GONE
                } else {
                    swipeRefresh.isRefreshing = false
                    recycler.refreshEmptyView()
                }
            }

            lifecycleScope.launch {
                viewModel.usersFlow.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }
    }
}
