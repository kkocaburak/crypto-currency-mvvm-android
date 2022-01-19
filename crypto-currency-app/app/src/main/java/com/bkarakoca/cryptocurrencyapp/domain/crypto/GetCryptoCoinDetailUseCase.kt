package com.bkarakoca.cryptocurrencyapp.domain.crypto

import com.bkarakoca.cryptocurrencyapp.data.repository.CryptoRepository
import com.bkarakoca.cryptocurrencyapp.internal.util.flow.FlowUseCase
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptodetail.model.CryptoCoinDetailUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCryptoCoinDetailUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : FlowUseCase<GetCryptoCoinDetailUseCase.Params, CryptoCoinDetailUIModel>() {

    data class Params(
        val cryptoCoinId: String
    )

    override suspend fun execute(params: Params): Flow<CryptoCoinDetailUIModel> =
        cryptoRepository.fetchCryptoCoinDetail(params.cryptoCoinId)
}