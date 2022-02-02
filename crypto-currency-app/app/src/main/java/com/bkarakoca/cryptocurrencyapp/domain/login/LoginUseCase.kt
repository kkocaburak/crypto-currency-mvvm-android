package com.bkarakoca.cryptocurrencyapp.domain.login

import com.bkarakoca.cryptocurrencyapp.data.remote.model.datastore.AuthResponse
import com.bkarakoca.cryptocurrencyapp.data.repository.AuthRepository
import com.bkarakoca.cryptocurrencyapp.internal.util.flow.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : FlowUseCase<LoginUseCase.Params, AuthResponse>() {

    data class Params(
        val emailText: String,
        val passwordText: String
    )

    override suspend fun execute(params: Params): Flow<AuthResponse> =
        authRepository.postLogin(params.emailText, params.passwordText)

}