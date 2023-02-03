package com.vitocuaderno.skeleton.features.splash

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
        compose = true
        super.onCreate(savedInstanceState)
//        viewModel
//            .state.observe(this) {
//                handleState(it)
//            }
//
//        supportActionBar?.hide()

        // TODO. for now overwrite xml layout view-binding.
        setContent {
//            val uiState = viewModel.state.observeAsState()
//            Text("Hello", style = TextStyle(color = Color.White))
            WelcomeScreen(viewModel = viewModel)
        }
    }

    @Composable
    fun WelcomeScreen(viewModel: SplashViewModel) {
        val uiState = viewModel.state.observeAsState()
        val splashState: SplashState? = uiState.value
        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(splashState?.msg ?: "", style = TextStyle(color = Color.Unspecified))
                LoginNavButton(vmState = uiState.value)
            }
        }
    }

    @Composable
    fun LoginNavButton(vmState: SplashState?) {
        if (vmState is SplashState.IsLoggedIn && !vmState.value) {
            Button(onClick = {
                launchOnboarding()
            }) {
                Text("Tap here to login")
            }
        }
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
