package com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto

import com.google.gson.annotations.SerializedName

data class CryptoCurrentPriceModel(
    @SerializedName("try")
    val turkishLira: Double? = null,
    @SerializedName("usd")
    val americanDollar: Double? = null
)
