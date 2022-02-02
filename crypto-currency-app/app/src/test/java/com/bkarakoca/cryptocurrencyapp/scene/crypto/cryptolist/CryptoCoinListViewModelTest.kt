package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bkarakoca.cryptocurrencyapp.domain.crypto.GetCryptoListUseCase
import com.bkarakoca.cryptocurrencyapp.internal.util.Failure
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class CryptoCoinListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var cryptoListUseCase: GetCryptoListUseCase

    lateinit var viewModel: CryptoCoinListViewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = CryptoCoinListViewModel(cryptoListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetchCryptoCoinList success should post cryptoCoinUIModelList`(): Unit = runBlocking {
        // given
        coEvery { cryptoListUseCase.execute(any()) } coAnswers { flow { emit(cryptoCoinUIModelList) } }

        // when
        viewModel.fetchCryptoCoinList()

        // then
        Assert.assertTrue(viewModel.cryptoList.value != null)
    }

    @Test
    fun `when fetchCryptoCoinList failure should not post cryptoCoinUIModelList`(): Unit = runBlocking {
        // given
        coEvery { cryptoListUseCase.execute(any()) } coAnswers { flow { throw Exception()} }

        // when
        viewModel.fetchCryptoCoinList()

        // then
        Assert.assertTrue(viewModel.cryptoList.value == null)
    }

    @Test
    fun `when fetchCryptoCoinList failure should post value to popup`(): Unit = runBlocking {
        val customFailure = Failure.CustomException("test")
        // given
        coEvery { cryptoListUseCase.execute(any()) } coAnswers { flow { throw customFailure } }

        // when
        viewModel.fetchCryptoCoinList()

        // then
        Assert.assertTrue(viewModel.popup.value != null)
    }

    @Test
    fun `when filterCryptoCoinList called with text2 should return filtered crypto list`(): Unit = runBlocking {
        val searchText = "d"
        val searchText2 = "bi"
        val searchText3 = "p"

        // given
        viewModel.postCryptoCoinList(cryptoCoinUIModelList)

        // when
        /** search - 1 */
        viewModel.filterCryptoCoinList(searchText)
        Assert.assertTrue(viewModel.filteredCryptoList.value?.size == 1)

        /** search - 2 */
        viewModel.filterCryptoCoinList(searchText2)
        Assert.assertTrue(viewModel.filteredCryptoList.value?.size == 2)

        /** search - 3 */
        viewModel.filterCryptoCoinList(searchText3)
        Assert.assertTrue(viewModel.filteredCryptoList.value?.size == 0)
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