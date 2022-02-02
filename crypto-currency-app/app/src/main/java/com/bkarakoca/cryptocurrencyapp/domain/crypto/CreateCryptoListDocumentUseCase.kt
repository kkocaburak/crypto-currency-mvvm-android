package com.bkarakoca.cryptocurrencyapp.domain.crypto

import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.repository.CryptoRepository
import com.bkarakoca.cryptocurrencyapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateCryptoListDocumentUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository
) : FlowUseCase<CreateCryptoListDocumentUseCase.Params, DataStoreResponse>() {

    data class Params(
        val path: String
    )

    override suspend fun execute(params: Params): Flow<DataStoreResponse> =
        cryptoRepository.createCryptoCoinFavoritesList(params.path)

}