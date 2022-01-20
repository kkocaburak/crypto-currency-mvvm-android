package com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto

import com.google.gson.annotations.SerializedName

data class CryptoDescriptionModel(
    @SerializedName("en")
    val englishDescription: String?,
    @SerializedName("tr")
    val turkishDescription: String?
)
