package com.bkarakoca.cryptocurrencyapp.data.repository.mapper.crypto

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto.CryptoCoinListResponseModel
import com.bkarakoca.cryptocurrencyapp.internal.util.ResourceProvider
import com.bkarakoca.cryptocurrencyapp.scene.cryptolist.model.CryptoCoinUIModel
import javax.inject.Inject

class CryptoCoinMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun toUIModel(responseModel: CryptoCoinListResponseModel): List<CryptoCoinUIModel> {
        return responseModel.filter { cryptoCoinResponseModel ->
            !cryptoCoinResponseModel.id.isNullOrEmpty()
        }.map { cryptoCoin ->
            CryptoCoinUIModel(
                id = cryptoCoin.id,
                coinName = cryptoCoin.name,
                coinImageUrl = cryptoCoin.image,
                coinSymbol = cryptoCoin.symbol,
                coinPrice = resourceProvider.getString(
                    R.string.crypto_coin_price_prefix,
                    cryptoCoin.currentPrice.toString()
                )
            )
        }
    }

}