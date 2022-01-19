package com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto

import com.google.gson.annotations.SerializedName

data class CryptoCoinDetailResponseModel(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val description: CryptoDescriptionModel?,
    val currentPrice: Double?,
    @SerializedName("image")
    val imageModel: CryptoImageModel?,
    @SerializedName("market_data")
    val marketData: CryptoMarketDataModel?,
    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String?
)