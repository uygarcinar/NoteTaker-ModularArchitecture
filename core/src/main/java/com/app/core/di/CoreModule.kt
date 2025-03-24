package com.app.core.di

import android.content.Context
import com.app.core.data.repository.SharedPreferencesManagerImpl
import com.app.core.domain.repository.IPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context : Context) : IPreferencesManager = SharedPreferencesManagerImpl(context)
}