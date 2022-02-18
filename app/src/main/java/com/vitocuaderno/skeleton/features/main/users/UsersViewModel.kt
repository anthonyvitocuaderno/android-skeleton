package com.vitocuaderno.skeleton.features.main.users

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vitocuaderno.skeleton.data.local.models.User
import com.vitocuaderno.skeleton.data.repository.user.UserRepository
import com.vitocuaderno.skeleton.features.common.BaseViewModel
import com.vitocuaderno.skeleton.utils.PAGE_SIZE
import com.vitocuaderno.skeleton.utils.PAGING_MAX_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    userRepository: UserRepository
) : BaseViewModel() {
    val users: Flow<PagingData<User>> = userRepository.getAll()
}
