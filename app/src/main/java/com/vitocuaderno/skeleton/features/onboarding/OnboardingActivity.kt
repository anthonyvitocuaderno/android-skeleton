package com.vitocuaderno.skeleton.features.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivityOnboardingBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_onboarding

    override val viewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this) {
            handleState(it)
        }
    }

    private fun handleState(state: OnboardingState) {
        when (state) {
            OnboardingState.Idle -> {
                // TODO
            }
        }
    }
}
