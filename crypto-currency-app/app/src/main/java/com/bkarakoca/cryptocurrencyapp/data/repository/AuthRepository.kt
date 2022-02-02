package com.bkarakoca.cryptocurrencyapp.data.repository

import com.bkarakoca.cryptocurrencyapp.data.remote.api.RemoteDataStore
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.AuthResponse
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val remoteDataStore: RemoteDataStore,
) {

    suspend fun postLogin(emailText: String, passwordText: String): Flow<AuthResponse> {
        return flow {
            remoteDataStore.postLogin(emailText, passwordText)
                .collect {
                    emit(it)
                }
        }
    }

    suspend fun postRegister(emailText: String, passwordText: String): Flow<DataStoreResponse> {
        return flow {
            remoteDataStore.postRegister(emailText, passwordText)
                .collect {
                    emit(it)
                }
        }
    }

}