package com.bkarakoca.cryptocurrencyapp.data.repository

import com.bkarakoca.cryptocurrencyapp.data.remote.datasource.crypto.CryptoRemoteDataSource
import com.bkarakoca.cryptocurrencyapp.data.repository.mapper.crypto.CryptoCoinMapper
import com.bkarakoca.cryptocurrencyapp.scene.cryptolist.model.CryptoCoinUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoRepository @Inject constructor(
    private val remoteDataSource: CryptoRemoteDataSource,
    private val mapper: CryptoCoinMapper
) {
    suspend fun fetchCryptoCoinList(): Flow<List<CryptoCoinUIModel>> = flow {
        remoteDataSource.fetchCryptoCoinList().collect {
            emit(mapper.toUIModel(it))
        }
    }
}