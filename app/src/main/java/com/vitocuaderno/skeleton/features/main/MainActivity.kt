package com.vitocuaderno.skeleton.features.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivityMainBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels()

    private lateinit var mainPagerAdapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel
            .state.observe(this) {
                handleState(it)
            }

        binding.apply {
            btnLogout.setOnClickListener {
                viewModel.logout()
            }

            mainPagerAdapter = MainPagerAdapter(this@MainActivity)
            pager.adapter = mainPagerAdapter
            TabLayoutMediator(tabs, pager) { tab, position ->
                mainPagerAdapter.getTitle(position)
                tab.text = mainPagerAdapter.getTitle(position)
            }.attach()
        }
    }

    private fun handleState(state: MainState) {
        when (state) {
            MainState.Busy -> {
                Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
            }
            MainState.IsLoggedOut -> {
                launchOnboarding()
            }
            else -> {
                // TODO
            }
        }
    }

    override fun onBackPressed() {
        if (binding.pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.pager.currentItem = binding.pager.currentItem - 1
        }
    }
}
