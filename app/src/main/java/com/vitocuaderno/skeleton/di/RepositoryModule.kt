package com.vitocuaderno.skeleton.di

import com.vitocuaderno.skeleton.data.repository.auth.AuthRepository
import com.vitocuaderno.skeleton.data.repository.auth.AuthRepositoryImpl
import com.vitocuaderno.skeleton.data.repository.task.TaskRepository
import com.vitocuaderno.skeleton.data.repository.task.TaskRepositoryImpl
import com.vitocuaderno.skeleton.data.repository.user.UserRepository
import com.vitocuaderno.skeleton.data.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun providesAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun providesUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    fun providesTaskRepository(repository: TaskRepositoryImpl): TaskRepository
}
