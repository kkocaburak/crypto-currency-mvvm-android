package com.bkarakoca.cryptocurrencyapp.internal.injection.module

import com.bkarakoca.cryptocurrencyapp.data.remote.api.CryptoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class CryptoModule {

    @Provides
    @Singleton
    fun provideCryptoService(retrofit: Retrofit): CryptoService =
        retrofit.create(CryptoService::class.java)

}