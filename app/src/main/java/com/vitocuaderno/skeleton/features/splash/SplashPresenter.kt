package com.vitocuaderno.skeleton.features.splash

import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val authRepository: AuthRepository,
) : SplashContract.Presenter {

    private var mView: SplashContract.View? = null

    override suspend fun checkSession() {
        delay(500)
        if (authRepository.getSession() != null) {
            mView?.navigateToMain()
        } else {
            mView?.navigateToOnboarding()
        }
    }

    override fun start() {
    }

    override fun setView(view: SplashContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }
}
