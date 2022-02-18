package com.vitocuaderno.skeleton.features.onboarding.register

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentRegisterBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import com.vitocuaderno.skeleton.features.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_register

    override val viewModel: RegisterViewModel by viewModels()

    override val title = "Register"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            handleState(it)
        }

        binding.apply {
            btnRegister.setOnClickListener {
                register()
            }

            tilConfirmPassword.editText!!.setOnEditorActionListener(
                TextView.OnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        register()
                        true
                    }
                    false
                }
            )
        }
    }

    private fun register() {
        viewModel.register(
            binding.tilUsername.editText!!.text.toString(),
            binding.tilPassword.editText!!.text.toString(),
            binding.tilConfirmPassword.editText!!.text.toString()
        )
    }

    private fun handleState(state: RegisterState) {
        when (state) {
            is RegisterState.Idle -> {
                resetToIdle(state.username)
            }
            RegisterState.Busy -> {
                setBusy()
            }
            RegisterState.Success -> {
                (requireActivity() as OnboardingActivity).launchMain()
            }
            is RegisterState.Failed -> {
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
        binding.tilConfirmPassword.editText?.setText("")
        binding.btnRegister.text = "Register"

        setUiEnabled(true)
    }

    private fun setBusy() {
        binding.btnRegister.text = "Please wait..."

        setUiEnabled(false)
    }

    private fun setUiEnabled(isEnabled: Boolean) {
        binding.tilUsername.isEnabled = isEnabled
        binding.tilPassword.isEnabled = isEnabled
        binding.tilConfirmPassword.isEnabled = isEnabled
        binding.btnRegister.isEnabled = isEnabled
    }
}
