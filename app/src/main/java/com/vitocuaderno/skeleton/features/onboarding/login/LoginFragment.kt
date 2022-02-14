package com.vitocuaderno.skeleton.features.onboarding.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentLoginBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_login

    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
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
                Toast.makeText(requireContext(), "SUCCESS!", Toast.LENGTH_SHORT).show()
            }
            is LoginState.Failed -> {
                Toast.makeText(requireContext(), "Failed! " + state.message, Toast.LENGTH_SHORT).show()
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
