package com.vitocuaderno.skeleton.features.onboarding.login

import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val authRepository: AuthRepository
) : LoginContract.Presenter {

    private var mView: LoginContract.View? = null

    override fun start() {
        // TODO
    }

    override fun setView(view: LoginContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }

    override suspend fun login(username: String, password: String) {
        mView?.showBusy()

        try {
            val token = authRepository.login(username, password)
            if (token.isNullOrEmpty()) {
                mView?.showFailed("unexpected error")
            } else {
                mView?.showSuccess()
            }
        } catch (e: UnknownHostException) {
            mView?.showFailed("cannot connect")
        } catch (e: HttpException) {
            mView?.showFailed("invalid username/password") // e.message
        }
    }
}
