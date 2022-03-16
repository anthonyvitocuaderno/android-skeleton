package com.vitocuaderno.skeleton.features.main

import com.vitocuaderno.skeleton.R
import com.vitocuaderno.skeleton.databinding.ActivityMainBinding
import com.vitocuaderno.skeleton.features.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int = R.layout.activity_main
}
