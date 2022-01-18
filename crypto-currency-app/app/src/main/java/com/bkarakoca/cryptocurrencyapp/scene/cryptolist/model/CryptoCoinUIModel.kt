package com.bkarakoca.cryptocurrencyapp.scene.cryptolist.model

import android.os.Parcelable
import com.bkarakoca.cryptocurrencyapp.base.ListAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoCoinUIModel(
    override val id: String?,
    val coinName: String? = null,
    val coinSymbol: String? = null,
    val coinPrice: String? = null,
    val coinImageUrl: String? = null
) : Parcelable, ListAdapterItem
