package com.bkarakoca.cryptocurrencyapp.domain.crypto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bkarakoca.cryptocurrencyapp.data.repository.CryptoRepository
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetCryptoListUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var cryptoRepository: CryptoRepository

    lateinit var useCase: GetCryptoListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetCryptoListUseCase(cryptoRepository)
    }

    @Test
    fun testExecute_Positive() = runBlocking {
        // given
        coEvery { cryptoRepository.fetchCryptoCoinList() } coAnswers { flow { emit(cryptoCoinUIModelList) } }

        // when
        useCase.execute(Unit).collect {
            Assert.assertTrue(it.size == 4)
            Assert.assertTrue(it.first().coinNameText == "doge")
        }
    }

    private val cryptoCoinUIModelList = listOf(
        CryptoCoinUIModel(
            "test",
            "doge",
            "doge",
            "test",
            "test"
        ),
        CryptoCoinUIModel(
            "test",
            "etherium",
            "eth",
            "test",
            "test"
        ),
        CryptoCoinUIModel(
            "test",
            "bitcoin",
            "btc",
            "test",
            "test"
        ),
        CryptoCoinUIModel(
            "test",
            "binance coin",
            "bnb",
            "test",
            "test"
        )
    )

}