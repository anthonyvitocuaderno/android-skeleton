package com.vitocuaderno.skeleton.features.onboarding.register

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentRegisterBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import com.vitocuaderno.skeleton.features.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), RegisterContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_register

    @Inject
    lateinit var presenter: RegisterPresenter

    override val title = "Register"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.start()

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
        lifecycleScope.launch {
            presenter.register(
                binding.tilUsername.editText!!.text.toString(),
                binding.tilPassword.editText!!.text.toString(),
                binding.tilConfirmPassword.editText!!.text.toString()
            )
        }
    }

    override fun resetToIdle(username: String?) {
        binding.tilUsername.editText?.setText(username)
        binding.tilPassword.editText?.setText("")
        binding.tilConfirmPassword.editText?.setText("")
        binding.btnRegister.text = "Register"

        setUiEnabled(true)
    }

    override fun showBusy() {
        binding.btnRegister.text = "Please wait..."

        setUiEnabled(false)
    }

    override fun showSuccess() {
        (requireActivity() as OnboardingActivity).launchMain()
    }

    override fun showFailed(message: String) {
        Toast.makeText(requireContext(), "Failed! $message", Toast.LENGTH_SHORT).show()
    }

    private fun setUiEnabled(isEnabled: Boolean) {
        binding.tilUsername.isEnabled = isEnabled
        binding.tilPassword.isEnabled = isEnabled
        binding.tilConfirmPassword.isEnabled = isEnabled
        binding.btnRegister.isEnabled = isEnabled
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
