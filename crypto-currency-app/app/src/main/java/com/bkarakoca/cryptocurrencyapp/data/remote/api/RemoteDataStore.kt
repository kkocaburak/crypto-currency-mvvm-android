package com.bkarakoca.cryptocurrencyapp.data.remote.api

import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.AuthResponse
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.crypto.CryptoCoinFireStoreResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataStore {
    suspend fun createUserDocument(email: String): Flow<DataStoreResponse>
    suspend fun updateUserFavoriteCryptoList(value: Any): Flow<DataStoreResponse>
    suspend fun fetchUserFavoriteCryptoList(): Flow<CryptoCoinFireStoreResponse>
    suspend fun postLogin(emailText: String, passwordText: String): Flow<AuthResponse>
    suspend fun postRegister(emailText: String, passwordText: String): Flow<DataStoreResponse>
}