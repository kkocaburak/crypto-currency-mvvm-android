package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptodetail.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoCoinDetailUIModel(
    val coinId: String,
    val coinHashingAlgorithm: String,
    val coinNameText: String,
    val coinSymbolText: String,
    val coinDescriptionText: String,
    val coinImageUrl: String?,
    val coinPriceText: String,
    val coinPriceChangeForDayText: String
) : Parcelable