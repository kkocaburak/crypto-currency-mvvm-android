package com.bkarakoca.cryptocurrencyapp.data.remote.datasource.crypto

import com.bkarakoca.cryptocurrencyapp.data.remote.BaseRemoteDataSource
import com.bkarakoca.cryptocurrencyapp.data.remote.api.CryptoService
import com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto.CryptoCoinDetailResponse
import com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto.CryptoCoinListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoRemoteDataSource @Inject constructor(
    private val service: CryptoService
) : BaseRemoteDataSource() {

    suspend fun fetchCryptoCoinList(): Flow<CryptoCoinListResponse> = invokeFlow {
        service.fetchCryptoCoinList()
    }

    suspend fun fetchCryptoCoinDetail(cryptoCoinId: String): Flow<CryptoCoinDetailResponse> = invokeFlow {
        service.fetchCryptoCoinDetail(cryptoCoinId)
    }

}