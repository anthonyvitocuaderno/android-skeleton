package com.vitocuaderno.skeleton.di

import android.app.Application
import android.content.Context
import com.vitocuaderno.skeleton.features.main.MainContract
import com.vitocuaderno.skeleton.features.main.MainPresenter
import com.vitocuaderno.skeleton.features.main.users.UsersContract
import com.vitocuaderno.skeleton.features.main.users.UsersPresenter
import com.vitocuaderno.skeleton.features.onboarding.OnboardingContract
import com.vitocuaderno.skeleton.features.onboarding.OnboardingPresenter
import com.vitocuaderno.skeleton.features.onboarding.login.LoginContract
import com.vitocuaderno.skeleton.features.onboarding.login.LoginPresenter
import com.vitocuaderno.skeleton.features.onboarding.register.RegisterContract
import com.vitocuaderno.skeleton.features.onboarding.register.RegisterPresenter
import com.vitocuaderno.skeleton.features.splash.SplashContract
import com.vitocuaderno.skeleton.features.splash.SplashPresenter
import dagger.Binds
import dagger.Module
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
abstract class AppModule {

    @ApplicationContext
    @Singleton
    @Binds
    abstract fun providesApplicationContext(app: Application): Context

    @Binds
    abstract fun providesSplashPresenter(presenter: SplashPresenter): SplashContract.Presenter

    @Binds
    abstract fun providesOnboardingPresenter(presenter: OnboardingPresenter): OnboardingContract.Presenter

    @Binds
    abstract fun providesLoginPresenter(presenter: LoginPresenter): LoginContract.Presenter

    @Binds
    abstract fun providesRegisterPresenter(presenter: RegisterPresenter): RegisterContract.Presenter

    @Binds
    abstract fun providesMainPresenter(presenter: MainPresenter): MainContract.Presenter

    @Binds
    abstract fun providesUsersPresenter(presenter: UsersPresenter): UsersContract.Presenter
}
