package com.vitocuaderno.skeleton.features.main.dummy

import android.os.Bundle
import android.view.View
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import com.vitocuaderno.skeleton.databinding.FragmentDummyBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import com.vitocuaderno.skeleton.features.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DummyFragment : BaseFragment<FragmentDummyBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_dummy

    override val title = "Dummy"

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            authRepository.logoutAsync()
            (requireActivity() as BaseActivity<*>).launchOnboarding()
        }
    }
}
