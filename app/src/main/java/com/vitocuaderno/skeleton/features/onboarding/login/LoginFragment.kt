package com.vitocuaderno.skeleton.features.onboarding.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentLoginBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import com.vitocuaderno.skeleton.features.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_login

    @Inject
    lateinit var presenter: LoginPresenter

    override val title = "Login"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.start()

        binding.apply {

            tilUsername.editText?.setText("eve.holt@reqres.in")

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
        lifecycleScope.launch {
            presenter.login(
                binding.tilUsername.editText!!.text.toString(),
                binding.tilPassword.editText!!.text.toString()
            )
        }
    }

    override fun resetToIdle(username: String?) {
        username?.let { binding.tilUsername.editText?.setText(it) }
        binding.tilPassword.editText?.setText("")
        binding.btnLogin.text = "Login"

        setUiEnabled(true)
    }

    override fun showBusy() {
        binding.btnLogin.text = "Please wait..."

        setUiEnabled(false)
    }

    private fun setUiEnabled(isEnabled: Boolean) {
        binding.tilUsername.isEnabled = isEnabled
        binding.tilPassword.isEnabled = isEnabled
        binding.btnLogin.isEnabled = isEnabled
        binding.btnRegister.isEnabled = isEnabled
    }

    override fun showSuccess() {
        (requireActivity() as OnboardingActivity).launchMain()
    }

    override fun showFailed(message: String) {
        Toast.makeText(requireContext(), "Failed! $message", Toast.LENGTH_SHORT).show()
        resetToIdle(null)
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
