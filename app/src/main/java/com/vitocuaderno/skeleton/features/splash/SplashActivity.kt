package com.vitocuaderno.skeleton.features.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivitySplashBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_splash

    override val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
            .state.observe(this) {
                handleState(it)
            }

        supportActionBar?.hide()
    }

    private fun handleState(state: SplashState) {
        binding.btnLogin.isVisible = false
        when (state) {
            is SplashState.IsLoggedIn -> {
                if (state.value) {
                    launchMain()
                } else {
                    // Disable auto redirect for now for the demo. Wait for button click.
                    binding.btnLogin.isVisible = true
                    binding.welcome = "Not logged in"
                    binding.btnLogin.setOnClickListener {
                        launchOnboarding()
                    }
                }
            }
            is SplashState.Initializing -> {
                binding.welcome = state.toString()
            }
            else -> {
//                Toast.makeText(this, state.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
