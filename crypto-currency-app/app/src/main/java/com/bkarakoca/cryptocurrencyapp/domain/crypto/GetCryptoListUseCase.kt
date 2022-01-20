package com.bkarakoca.cryptocurrencyapp.domain.crypto

import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import com.bkarakoca.cryptocurrencyapp.data.repository.CryptoRepository
import com.bkarakoca.cryptocurrencyapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCryptoListUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : FlowUseCase<Unit, List<CryptoCoinUIModel>>() {

    override suspend fun execute(params: Unit): Flow<List<CryptoCoinUIModel>> =
        cryptoRepository.fetchCryptoCoinList()
}