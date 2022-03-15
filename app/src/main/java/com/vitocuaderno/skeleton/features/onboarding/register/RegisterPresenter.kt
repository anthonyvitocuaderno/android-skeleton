package com.vitocuaderno.skeleton.features.onboarding.register

import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class RegisterPresenter @Inject constructor(
    private val authRepository: AuthRepository
) : RegisterContract.Presenter {

    private var mView: RegisterContract.View? = null

    override fun start() {
        // TODO
    }

    override fun setView(view: RegisterContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }

    override fun register(username: String, password: String, confirmPassword: String) {

        if (password != confirmPassword) {
            mView?.showFailed("passwords do not match")
            return
        }

        mView?.showBusy()

        GlobalScope.launch {
            try {
                val token = authRepository.registerAsync(username, password).await()
                if (token.isNullOrEmpty()) {
                    mView?.showFailed("unexpected error")
                } else {
                    mView?.showSuccess()
                }
            } catch (e: UnknownHostException) {
                mView?.showFailed("cannot connect")
            } catch (e: HttpException) {
                mView?.showFailed("invalid username/password")
            } finally {
                mView?.resetToIdle(username)
            }
        }
    }
}
