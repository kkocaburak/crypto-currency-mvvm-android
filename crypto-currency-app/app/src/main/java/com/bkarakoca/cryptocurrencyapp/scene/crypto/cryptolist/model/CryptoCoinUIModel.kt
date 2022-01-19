package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model

import android.os.Parcelable
import com.bkarakoca.cryptocurrencyapp.base.ListAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoCoinUIModel(
    override val id: String,
    val coinNameText: String,
    val coinSymbolText: String,
    val coinPriceText: String,
    val coinImageUrl: String
) : Parcelable, ListAdapterItem
