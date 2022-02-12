package com.vitocuaderno.skeleton.features.onboarding

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivityLoginBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_login

    override val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this) {
            handleState(it)
        }

        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.login(
                    tilUsername.editText!!.text.toString(),
                    tilPassword.editText!!.text.toString()
                )
            }
        }
    }

    private fun handleState(state: LoginState) {
        when (state) {
            is LoginState.Idle -> {
                resetToIdle(state.username)
            }
            LoginState.Busy -> {
                setBusy()
            }
            LoginState.Success -> {
                Toast.makeText(this, "SUCCESS!", Toast.LENGTH_SHORT).show()
            }
            is LoginState.Failed -> {
                Toast.makeText(this, "Failed! " + state.message, Toast.LENGTH_SHORT).show()
            }
            else -> {
                // TODO
            }
        }
    }

    private fun resetToIdle(username: String) {
        binding.tilUsername.editText?.setText(username)
        binding.tilPassword.editText?.setText("")
        binding.btnLogin.text = "Login"

        setUiEnabled(true)
    }

    private fun setBusy() {
        binding.btnLogin.text = "Please wait..."

        setUiEnabled(false)
    }

    private fun setUiEnabled(isEnabled: Boolean) {
        binding.tilUsername.isEnabled = isEnabled
        binding.tilPassword.isEnabled = isEnabled
        binding.btnLogin.isEnabled = isEnabled
    }
}
