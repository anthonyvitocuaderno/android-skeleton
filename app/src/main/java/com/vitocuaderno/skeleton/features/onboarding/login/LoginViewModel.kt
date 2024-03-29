package com.vitocuaderno.skeleton.features.onboarding.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import com.vitocuaderno.skeleton.features.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<LoginState>(LoginState.Idle("eve.holt@reqres.in"))
    val state: LiveData<LoginState> = _state

    override fun start() {
        super.start()
        // TODO
    }

    fun login(username: String, password: String) {
        _state.postValue(LoginState.Busy)
        viewModelScope.launch {
            try {
                val token = authRepository.login(username, password)
                if (token.isEmpty()) {
                    _state.postValue(LoginState.Failed("unexpected error"))
                } else {
                    _state.postValue(LoginState.Success)
                }
            } catch (e: UnknownHostException) {
                _state.postValue(LoginState.Failed("cannot connect"))
            } catch (e: HttpException) {
                _state.postValue(LoginState.Failed("invalid username/password")) // e.message
            }
        }
    }
}
