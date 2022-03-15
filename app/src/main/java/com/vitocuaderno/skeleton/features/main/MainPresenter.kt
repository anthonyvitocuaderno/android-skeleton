package com.vitocuaderno.skeleton.features.main

import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val authRepository: AuthRepository
) : MainContract.Presenter {

    private var mView: MainContract.View? = null

    override fun logout() {
        GlobalScope.launch {
            mView?.showBusy()
            authRepository.logoutAsync()
            mView?.logout()
        }
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
