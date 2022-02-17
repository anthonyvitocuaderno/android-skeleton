package com.vitocuaderno.skeleton.features.main.dummy

import androidx.fragment.app.viewModels
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentDummyBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DummyFragment : BaseFragment<FragmentDummyBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_dummy

    override val viewModel: DummyViewModel by viewModels()
}
