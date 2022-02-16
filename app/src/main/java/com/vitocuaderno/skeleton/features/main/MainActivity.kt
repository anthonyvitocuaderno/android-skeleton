package com.vitocuaderno.skeleton.features.main

import android.os.Bundle
import androidx.activity.viewModels
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivityMainBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel
            .state.observe(this) {
                handleState(it)
            }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun handleState(state: MainState) {
        when (state) {
            MainState.IsLoggedOut -> {
                launchOnboarding()
            }
            else -> {
                // TODO
            }
        }
    }
}
