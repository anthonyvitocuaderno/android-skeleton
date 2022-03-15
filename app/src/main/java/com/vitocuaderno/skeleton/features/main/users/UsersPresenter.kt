package com.vitocuaderno.skeleton.features.main.users

import androidx.paging.PagingData
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    userRepository: UserRepository
) : UsersContract.Presenter {

    private var mView: UsersContract.View? = null

    private val usersFlow: Flow<PagingData<User>> = userRepository.getAll()

    override fun start() {
        GlobalScope.launch {
            usersFlow.collectLatest { pagingData ->
                mView?.submitList(pagingData)
            }
        }
    }

    override fun setView(view: UsersContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }
}
