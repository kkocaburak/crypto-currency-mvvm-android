package com.bkarakoca.cryptocurrencyapp.domain.login

import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.DataStoreResponse
import com.bkarakoca.cryptocurrencyapp.data.repository.AuthRepository
import com.bkarakoca.cryptocurrencyapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : FlowUseCase<RegisterUseCase.Params, DataStoreResponse>() {

    data class Params(
        val emailText: String,
        val passwordText: String
    )

    override suspend fun execute(params: Params): Flow<DataStoreResponse> =
        authRepository.postRegister(params.emailText, params.passwordText)

}