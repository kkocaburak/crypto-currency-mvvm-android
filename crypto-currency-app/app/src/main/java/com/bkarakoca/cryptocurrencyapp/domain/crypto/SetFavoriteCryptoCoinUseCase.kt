package com.bkarakoca.cryptocurrencyapp.domain.crypto

import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.repository.CryptoRepository
import com.bkarakoca.cryptocurrencyapp.internal.util.flow.FlowUseCase
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetFavoriteCryptoCoinUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : FlowUseCase<SetFavoriteCryptoCoinUseCase.Params, DataStoreResponse>() {

    data class Params(
        val cryptoCoinUIModel: CryptoCoinUIModel
    )

    override suspend fun execute(params: Params): Flow<DataStoreResponse> =
        cryptoRepository.setFavoriteCryptoCoin(params.cryptoCoinUIModel)

}