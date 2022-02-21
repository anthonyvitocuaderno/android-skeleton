package com.vitocuaderno.skeleton.features.main.users

import androidx.paging.PagingData
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.data.repository.user.UserRepository
import com.vitocuaderno.skeleton.features.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    userRepository: UserRepository
) : BaseViewModel() {
    val usersFlow: Flow<PagingData<User>> = userRepository.getAll()
}
