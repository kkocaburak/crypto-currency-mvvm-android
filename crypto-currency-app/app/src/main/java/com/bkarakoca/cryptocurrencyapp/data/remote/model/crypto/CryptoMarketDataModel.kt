package com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto

import com.google.gson.annotations.SerializedName

data class CryptoMarketDataModel(
    @SerializedName("price_change_percentage_24h")
    val coinPriceChangeForDay: Double? = null,
    @SerializedName("current_price")
    val currentPriceModel: CryptoCurrentPriceModel? = null
)