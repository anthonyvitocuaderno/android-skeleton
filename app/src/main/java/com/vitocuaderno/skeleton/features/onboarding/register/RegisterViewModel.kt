package com.vitocuaderno.skeleton.features.onboarding.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import com.vitocuaderno.skeleton.features.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<RegisterState>(RegisterState.Idle("eve.holt@reqres.in"))
    val state: LiveData<RegisterState> = _state

    override fun start() {
        super.start()
    }

    fun register(username: String, password: String, confirmPassword: String) {

        if (password != confirmPassword) {
            _state.postValue(RegisterState.Failed("passwords do not match"))
            return
        }

        _state.postValue(RegisterState.Busy)
        viewModelScope.launch {
            try {
                val token = authRepository.registerAsync(username, password).await()
                if (token.isNullOrEmpty()) {
                    _state.postValue(RegisterState.Failed("unexpected error"))
                } else {
                    _state.postValue(RegisterState.Success)
                }
            } catch (e: UnknownHostException) {
                _state.postValue(RegisterState.Failed("cannot connect"))
            } catch (e: HttpException) {
                _state.postValue(RegisterState.Failed("invalid username/password")) // e.message
            } finally {
                delay(100)
                _state.postValue(RegisterState.Idle(username))
            }
        }
    }
}
