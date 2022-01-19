package com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto

class CryptoCoinListResponseModel : ArrayList<CryptoCoinResponseModel>()

data class CryptoCoinResponseModel(
    val id: String?,
    val symbol: String?,
    val name: String?,
    val image: String?,
    val currentPrice: Double?
)