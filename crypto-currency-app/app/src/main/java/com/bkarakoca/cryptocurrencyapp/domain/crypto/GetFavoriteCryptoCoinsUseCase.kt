package com.bkarakoca.cryptocurrencyapp.domain.crypto

import com.bkarakoca.cryptocurrencyapp.data.repository.CryptoRepository
import com.bkarakoca.cryptocurrencyapp.internal.util.flow.FlowUseCase
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteCryptoCoinsUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : FlowUseCase<Unit, List<CryptoCoinUIModel>>() {

    override suspend fun execute(params: Unit): Flow<List<CryptoCoinUIModel>> =
        cryptoRepository.fetchCryptoCoinFavoritesList()
}