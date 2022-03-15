package com.vitocuaderno.skeleton.features.main

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivityMainBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), MainContract.View {
    override fun getLayoutId(): Int = R.layout.activity_main

    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var mainPagerAdapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.start()

        binding.apply {
            btnLogout.setOnClickListener {
                presenter.logout()
            }

            mainPagerAdapter = MainPagerAdapter(this@MainActivity)
            pager.adapter = mainPagerAdapter
            TabLayoutMediator(tabs, pager) { tab, position ->
                mainPagerAdapter.getTitle(position)
                tab.text = mainPagerAdapter.getTitle(position)
            }.attach()
        }
    }

    override fun onBackPressed() {
        if (binding.pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.pager.currentItem = binding.pager.currentItem - 1
        }
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
        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
    }

    override fun showFailed(message: String) {
        TODO("Not yet implemented")
    }

    override fun logout() {
        launchOnboarding()
    }
}
