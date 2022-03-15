package com.vitocuaderno.skeleton.features.splash

import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val authRepository: AuthRepository,
) : SplashContract.Presenter {

    private var mView: SplashContract.View? = null

    override fun start() {

        GlobalScope.launch {
            delay(500)
            if (authRepository.getSession() != null) {
                mView?.navigateToMain()
            } else {
                mView?.navigateToOnboarding()
            }
        }
    }

    override fun setView(view: SplashContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }
}
