package com.bkarakoca.cryptocurrencyapp.data.repository.mapper.crypto

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.data.remote.model.crypto.CryptoCoinDetailResponseModel
import com.bkarakoca.cryptocurrencyapp.internal.extension.toPercentageString
import com.bkarakoca.cryptocurrencyapp.internal.util.ResourceProvider
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptodetail.model.CryptoCoinDetailUIModel
import javax.inject.Inject

class CryptoCoinDetailMapper @Inject constructor(
    private val resourceProvider: ResourceProvider
) {

    fun toUIModel(responseModel: CryptoCoinDetailResponseModel): CryptoCoinDetailUIModel {
        with(resourceProvider) {
            return CryptoCoinDetailUIModel(
                coinId = responseModel.id ?: "",
                coinHashingAlgorithm = responseModel.hashingAlgorithm
                    ?: getString(R.string.crypto_empty_hashing_algorithm),
                coinNameText = getCoinNameText(responseModel.name),
                coinSymbolText = getCoinSymbolText(responseModel.symbol),
                coinDescriptionText = responseModel.description?.englishDescription // TODO : fix this by localization
                    ?: getString(R.string.crypto_empty_description),
                coinImageUrl = responseModel.imageModel?.large,
                coinPriceText = getCoinPriceText(responseModel.currentPrice),
                coinPriceChangeForDayText = getCoinPricePercentageText(responseModel.marketData?.coinPriceChangeForDay)
            )
        }
    }

    private fun getCoinNameText(coinName: String?): String {
        return coinName?.let {
            resourceProvider.getString(R.string.crypto_coin_name, it)
        } ?: resourceProvider.getString(R.string.crypto_empty_name)
    }

    private fun getCoinSymbolText(coinSymbol: String?): String {
        return coinSymbol?.let {
            resourceProvider.getString(R.string.crypto_coin_symbol, it)
        } ?: resourceProvider.getString(R.string.crypto_empty_symbol)
    }

    private fun getCoinPriceText(currentPrice: Double?): String {
        return currentPrice?.let {
            resourceProvider.getString(R.string.crypto_coin_price_prefix, it.toString())
        } ?: resourceProvider.getString(R.string.crypto_empty_price)
    }

    private fun getCoinPricePercentageText(coinPriceChangeForDay: Double?): String {
        return coinPriceChangeForDay?.let {
            resourceProvider.getString(
                R.string.crypto_coin_price_change_percentage,
                it.toPercentageString()
            )
        } ?: resourceProvider.getString(R.string.crypto_empty_coin_price_change_percentage)
    }

}