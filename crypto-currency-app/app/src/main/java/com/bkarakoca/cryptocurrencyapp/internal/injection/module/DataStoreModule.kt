package com.bkarakoca.cryptocurrencyapp.internal.injection.module

import com.bkarakoca.cryptocurrencyapp.data.remote.api.RemoteDataStore
import com.bkarakoca.cryptocurrencyapp.data.remote.datasource.firebase.FireStoreDataStore
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(gson: Gson): RemoteDataStore = FireStoreDataStore(gson)
}