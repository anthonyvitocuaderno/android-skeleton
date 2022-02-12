package com.vitocuaderno.skeleton.di

import android.app.Application
import android.content.Context
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
}
