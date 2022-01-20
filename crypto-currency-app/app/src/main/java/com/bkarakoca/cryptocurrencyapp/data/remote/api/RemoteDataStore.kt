package com.bkarakoca.cryptocurrencyapp.data.remote.api

import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.crypto.CryptoCoinFireStoreModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataStore {
    suspend fun saveDocument(path: String, value: Any): Flow<DataStoreResponse>
    suspend fun getDocument(path: String): Flow<CryptoCoinFireStoreModel>
}