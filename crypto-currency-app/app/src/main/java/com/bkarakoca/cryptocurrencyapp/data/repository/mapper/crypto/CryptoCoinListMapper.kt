package com.bkarakoca.cryptocurrencyapp.data.repository.mapper.crypto

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto.CryptoCoinListResponseModel
import com.bkarakoca.cryptocurrencyapp.internal.util.ResourceProvider
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import javax.inject.Inject

class CryptoCoinListMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun toUIModel(responseModel: CryptoCoinListResponseModel): List<CryptoCoinUIModel> {
        return responseModel.filter {
            (!it.id.isNullOrEmpty()
                    && !it.name.isNullOrEmpty()
                    && !it.symbol.isNullOrEmpty())
        }.map { cryptoCoin ->
            CryptoCoinUIModel(
                id = cryptoCoin.id ?: "",
                coinNameText = cryptoCoin.name ?: "",
                coinImageUrl = cryptoCoin.image ?: "",
                coinSymbolText = cryptoCoin.symbol ?: "",
                coinPriceText = resourceProvider.getString(
                    R.string.crypto_coin_price_prefix,
                    cryptoCoin.currentPrice.toString()
                )
            )
        }
    }

}