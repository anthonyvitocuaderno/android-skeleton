package com.vitocuaderno.skeleton.features.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.FragmentMainBinding
import com.vitocuaderno.skeleton.features.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(), MainContract.View {

    override fun getLayoutId(): Int = R.layout.fragment_main

    override val title = "Main"

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var mainPagerAdapter: MainPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            mainPagerAdapter = MainPagerAdapter(requireActivity())
            pager.adapter = mainPagerAdapter
            TabLayoutMediator(tabs, pager) { tab, position ->
                mainPagerAdapter.getTitle(position)
                tab.text = mainPagerAdapter.getTitle(position)
            }.attach()
        }

        presenter.start()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsetView()
    }

    override fun resetToIdle() {
        TODO("Not yet implemented")
    }

    override fun showBusy() {
        Toast.makeText(requireContext(), "Please wait...", Toast.LENGTH_SHORT).show()
    }

    override fun showFailed(message: String) {
        TODO("Not yet implemented")
    }
}
