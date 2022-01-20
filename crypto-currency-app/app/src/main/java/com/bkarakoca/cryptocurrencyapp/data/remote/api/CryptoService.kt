package com.bkarakoca.cryptocurrencyapp.data.remote.api

import com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto.CryptoCoinDetailResponseModel
import com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto.CryptoCoinListResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoService {

    @GET(BINANCE_CRYPTO_COIN_LIST)
    suspend fun fetchCryptoCoinList(): CryptoCoinListResponseModel

    @GET(BINANCE_CRYPTO_COIN_DETAIL)
    suspend fun fetchCryptoCoinDetail(@Path(BINANCE_CRYPTO_COIN_ID) cryptoCoinId: String): CryptoCoinDetailResponseModel

    companion object {
        const val BINANCE_CRYPTO_COIN_LIST =
            "coins/markets?vs_currency=usd&category=binance-smart-chain&order=market_cap_desc&per_page=100&page=1&sparkline=false"

        const val BINANCE_CRYPTO_COIN_ID = "id"
        const val BINANCE_CRYPTO_COIN_DETAIL = "coins/{$BINANCE_CRYPTO_COIN_ID}"
    }

}