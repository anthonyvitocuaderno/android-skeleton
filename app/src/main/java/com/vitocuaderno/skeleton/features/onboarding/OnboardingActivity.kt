package com.vitocuaderno.skeleton.features.onboarding

import android.os.Bundle
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivityOnboardingBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(), OnboardingContract.View {
    override fun getLayoutId(): Int = R.layout.activity_onboarding

    @Inject
    lateinit var presenter: OnboardingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.start()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsetView()
    }
}
