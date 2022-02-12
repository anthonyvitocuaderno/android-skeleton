package com.vitocuaderno.skeleton.features.splash

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivitySplashBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_splash

    protected val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
            .state.observe(this) {
                handleState(it)
            }
    }

    private fun handleState(state: SplashState) {
        Toast.makeText(this, state.toString(), Toast.LENGTH_SHORT).show()
    }
}