package com.vitocuaderno.skeleton.features.main

import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val authRepository: AuthRepository
) : MainContract.Presenter {

    private var mView: MainContract.View? = null

    override suspend fun logout() {
        mView?.showBusy()
        authRepository.logoutAsync()
        mView?.logout()
    }

    override fun start() {
        // TODO
    }

    override fun setView(view: MainContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }
}
