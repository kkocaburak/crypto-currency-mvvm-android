package com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.crypto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class CryptoCoinFireStoreResponse: ArrayList<CryptoCoinFireStoreModel>()

@Parcelize
data class CryptoCoinFireStoreModel(
    val coinId: String? = "",
    val coinNameText: String?,
    val coinSymbolText: String?,
    val coinPriceText: String?,
    val coinImageUrl: String?
) : Parcelable