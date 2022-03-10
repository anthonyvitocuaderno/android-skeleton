package com.vitocuaderno.skeleton.features.onboarding.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentLoginBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import com.vitocuaderno.skeleton.features.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_login

    override val viewModel: LoginViewModel by viewModels()

    override val title = "Login"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            handleState(it)
        }

        binding.apply {
            btnLogin.setOnClickListener {
                login()
            }

            btnRegister.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )
            }

            tilPassword.editText!!.setOnEditorActionListener(
                OnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        login()
                        true
                    }
                    false
                }
            )
        }
    }

    private fun login() {
        viewModel.login(
            binding.tilUsername.editText!!.text.toString(),
            binding.tilPassword.editText!!.text.toString()
        )
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
                (requireActivity() as OnboardingActivity).launchMain()
            }
            is LoginState.Failed -> {
                Toast.makeText(requireContext(), "Failed! " + state.message, Toast.LENGTH_SHORT).show()
                resetToIdle()
            }
            else -> {
                // TODO
            }
        }
    }

    private fun resetToIdle(username: String?) {
        username?.let { binding.tilUsername.editText?.setText(it) }
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
        binding.btnRegister.isEnabled = isEnabled
    }
}
