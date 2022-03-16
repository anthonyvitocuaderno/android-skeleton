package com.vitocuaderno.skeleton.features.main.users

import androidx.paging.PagingData
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    userRepository: UserRepository
) : UsersContract.Presenter {

    private var mView: UsersContract.View? = null

    val usersFlow: Flow<PagingData<User>> = userRepository.getAll()

    override fun start() {
    }

    override fun setView(view: UsersContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }
}
