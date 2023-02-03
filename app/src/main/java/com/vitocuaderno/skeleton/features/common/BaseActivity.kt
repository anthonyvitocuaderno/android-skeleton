package com.vitocuaderno.skeleton.features.common

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.vitocuaderno.skeleton.features.main.MainActivity
import com.vitocuaderno.skeleton.features.onboarding.OnboardingActivity
import com.vitocuaderno.skeleton.features.splash.SplashActivity

/**
 * Automatically initializes ViewDataBinding class for your activity.
 */
abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    abstract val viewModel: BaseViewModel

    var compose = false
    lateinit var binding: B

    @LayoutRes
    abstract fun getLayoutId(): Int

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (!compose) {
            binding = DataBindingUtil.setContentView(
                this,
                getLayoutId()
            )
            binding.lifecycleOwner = this
        }

        viewModel.start()
    }

    fun launchMain() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        this.finishAffinity()
    }

    protected fun launchOnboarding() {
        val intent = Intent(this, OnboardingActivity::class.java)
        this.startActivity(intent)
        this.finishAffinity()
    }

    protected fun launchSplash() {
        val intent = Intent(this, SplashActivity::class.java)
        this.startActivity(intent)
        this.finishAffinity()
    }
}
