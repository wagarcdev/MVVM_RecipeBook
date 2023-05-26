package com.arcieri.wagner.mvvm_recipebook.data.remote

import com.arcieri.wagner.mvvm_recipebook.data.remote.auth.AuthService
import com.arcieri.wagner.mvvm_recipebook.data.remote.auth.AuthServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteBindModule {
    @Binds
    @Singleton
    fun bindsAuthService(
        impl: AuthServiceImpl
    ): AuthService
}