package com.vitocuaderno.skeleton.di

import com.vitocuaderno.skeleton.features.main.MainContract
import com.vitocuaderno.skeleton.features.main.MainPresenter
import com.vitocuaderno.skeleton.features.main.todos.TodoListContract
import com.vitocuaderno.skeleton.features.main.todos.TodoListPresenter
import com.vitocuaderno.skeleton.features.main.todos.add.AddTaskContract
import com.vitocuaderno.skeleton.features.main.todos.add.AddTaskPresenter
import com.vitocuaderno.skeleton.features.main.todos.tododetail.TodoDetailContract
import com.vitocuaderno.skeleton.features.main.todos.tododetail.TodoDetailPresenter
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

@Module
abstract class PresenterModule {

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

    @Binds
    abstract fun providesTodoListPresenter(presenter: TodoListPresenter): TodoListContract.Presenter

    @Binds
    abstract fun providesAddTaskPresenter(presenter: AddTaskPresenter): AddTaskContract.Presenter

    @Binds
    abstract fun providesTodoDetailPresenter(presenter: TodoDetailPresenter): TodoDetailContract.Presenter
}
