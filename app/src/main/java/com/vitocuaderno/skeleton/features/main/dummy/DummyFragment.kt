package com.vitocuaderno.skeleton.features.main.dummy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentDummyBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DummyFragment : BaseFragment<FragmentDummyBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_dummy

    override val viewModel: DummyViewModel by viewModels()
    override val title = "Dummy"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            handleState(it)
        }

        binding.apply {

            lifecycleOwner = viewLifecycleOwner
        }

        viewModel.start()
    }

    private fun handleState(state: DummyState) {
        when (state) {
            is DummyState.Welcome -> {
                binding.welcome = state.msg
            }
            else -> {
                // TODO
            }
        }
    }
}
