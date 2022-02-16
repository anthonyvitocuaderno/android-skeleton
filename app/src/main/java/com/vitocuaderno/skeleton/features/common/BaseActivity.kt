package com.vitocuaderno.skeleton.features.common

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.vitocuaderno.skeleton.features.main.MainActivity
import com.vitocuaderno.skeleton.features.onboarding.OnboardingActivity

/**
 * Automatically initializes ViewDataBinding class for your activity.
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    abstract val viewModel: BaseViewModel

    lateinit var binding: B

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding = DataBindingUtil.setContentView(
            this,
            getLayoutId()
        )
        binding.lifecycleOwner = this

        viewModel.start()
    }

    fun launchMain() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        this.finishAffinity()
    }

    protected  fun launchOnboarding() {
        val intent = Intent(this, OnboardingActivity::class.java)
        this.startActivity(intent)
        this.finishAffinity()
    }
}
