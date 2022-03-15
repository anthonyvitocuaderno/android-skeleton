package com.vitocuaderno.skeleton.features.main.users

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.databinding.FragmentUsersBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import com.vitocuaderno.skeleton.features.common.BaseLoaderStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragment : BaseFragment<FragmentUsersBinding>(), UsersContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_users

    @Inject
    lateinit var presenter: UsersPresenter

    override val title = "Users"

    private var adapter: UsersAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.start()

        binding.apply {

            lifecycleOwner = viewLifecycleOwner

            adapter = UsersAdapter()
            val loaderStateAdapter = BaseLoaderStateAdapter { adapter?.retry() }
            recycler.adapter = adapter?.withLoadStateFooter(loaderStateAdapter)
            recycler.setEmptyView(emptyView)
            swipeRefresh.setOnRefreshListener {
                adapter?.refresh()
            }
            adapter?.addLoadStateListener {
                // TODO move to business logic
                if (it.refresh is LoadState.Loading) {
                    swipeRefresh.isRefreshing = true
                    emptyView.visibility = View.GONE
                } else {
                    swipeRefresh.isRefreshing = false
                    recycler.refreshEmptyView()
                }
            }
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

    override fun resetToIdle() {
        // TODO
    }

    override fun showBusy() {
        // TODO
    }

    override fun showEmptyList() {
        // TODO
    }

    override fun submitList(source: PagingData<User>) {
        lifecycleScope.launch {
            adapter?.submitData(source)
        }
    }
}
