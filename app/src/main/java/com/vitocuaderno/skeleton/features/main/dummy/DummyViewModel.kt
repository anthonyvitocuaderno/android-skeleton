package com.vitocuaderno.skeleton.features.main.dummy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import com.vitocuaderno.skeleton.data.repository.user.UserRepository
import com.vitocuaderno.skeleton.features.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DummyViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<DummyState>(DummyState.CheckingSession)
    val state: LiveData<DummyState> = _state

    override fun start() {
        super.start()
        viewModelScope.launch {
            authRepository.getSession()?.let {
                val user = userRepository.getAsync(it.id)
                _state.postValue(DummyState.Welcome("Hello ${user.firstName} ${user.lastName}, You are now logged in"))
            }
        }
    }
}
