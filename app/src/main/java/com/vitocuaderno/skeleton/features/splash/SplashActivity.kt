package com.vitocuaderno.skeleton.features.splash

import android.os.Bundle
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivitySplashBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(), SplashContract.View {
    override fun getLayoutId(): Int = R.layout.activity_splash

    @Inject
    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        presenter.start()
    }

    override fun navigateToOnboarding() {
        launchOnboarding()
    }

    override fun navigateToMain() {
        launchMain()
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
