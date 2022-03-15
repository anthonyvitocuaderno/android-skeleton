package com.vitocuaderno.skeleton.features.onboarding

import javax.inject.Inject

class OnboardingPresenter @Inject constructor() : OnboardingContract.Presenter {

    private var mView: OnboardingContract.View? = null

    override fun start() {
        // TODO
    }

    override fun setView(view: OnboardingContract.View) {
        mView = view
    }

    override fun unsetView() {
        mView = null
    }
}
