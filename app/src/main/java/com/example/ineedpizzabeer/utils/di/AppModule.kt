package com.example.ineedpizzabeer.utils.di

import android.app.Application
import android.content.Context
import com.example.ineedpizzabeer.presentation.ui.MainApplication
import com.example.ineedpizzabeer.utils.AppCoroutineContextProvider
import com.example.ineedpizzabeer.utils.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun app(application: Application): MainApplication = application as MainApplication

    @Provides
    @Singleton
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun contextProvider(): CoroutineContextProvider = AppCoroutineContextProvider()
}