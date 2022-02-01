package com.bkarakoca.cryptocurrencyapp.data.repository.mapper.crypto

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.crypto.CryptoCoinFireStoreResponse
import com.bkarakoca.cryptocurrencyapp.internal.util.ResourceProvider
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import javax.inject.Inject

class CryptoCoinFireStoreMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun toUIModel(responseModel: CryptoCoinFireStoreResponse): List<CryptoCoinUIModel> {
        return responseModel.filter {
            (!it.coinNameText.isNullOrEmpty() && !it.coinPriceText.isNullOrEmpty())
        }.map { cryptoCoin ->
            CryptoCoinUIModel(
                id = cryptoCoin.coinId ?: "",
                coinNameText = cryptoCoin.coinNameText ?: "",
                coinImageUrl = cryptoCoin.coinImageUrl ?: "",
                coinSymbolText = cryptoCoin.coinSymbolText ?: "",
                coinPriceText = resourceProvider.getString(
                    R.string.crypto_coin_current_price,
                    cryptoCoin.coinPriceText.toString()
                )
            )
        }
    }

}