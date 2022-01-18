package com.bkarakoca.cryptocurrencyapp.data.remote.api

import com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto.CryptoCoinListResponseModel
import retrofit2.http.GET

interface CryptoService {

    @GET(BINANCE_CRYPTO_COIN_LIST)
    suspend fun fetchCryptoCoinList(): CryptoCoinListResponseModel

    companion object {
        const val BINANCE_CRYPTO_COIN_LIST =
            "coins/markets?vs_currency=usd&category=binance-smart-chain&order=market_cap_desc&per_page=100&page=1&sparkline=false"
    }

}