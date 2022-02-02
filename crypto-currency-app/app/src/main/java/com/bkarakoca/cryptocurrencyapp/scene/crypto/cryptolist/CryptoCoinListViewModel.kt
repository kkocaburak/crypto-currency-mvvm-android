package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bkarakoca.cryptocurrencyapp.base.BaseViewModel
import com.bkarakoca.cryptocurrencyapp.domain.crypto.GetCryptoListUseCase
import com.bkarakoca.cryptocurrencyapp.internal.extension.launch
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CryptoCoinListViewModel @Inject constructor(
    private val cryptoListUseCase: GetCryptoListUseCase
) : BaseViewModel() {

    private val _cryptoList = MutableLiveData<List<CryptoCoinUIModel>>()
    val cryptoList: LiveData<List<CryptoCoinUIModel>> get() = _cryptoList

    private val _filteredCryptoList = MutableLiveData<List<CryptoCoinUIModel>>()
    val filteredCryptoList: LiveData<List<CryptoCoinUIModel>> get() = _filteredCryptoList

    fun fetchCryptoCoinList() = launch {
        cryptoListUseCase.execute(Unit)
            .collect { cryptoCoinUIModelList ->
                postCryptoCoinList(cryptoCoinUIModelList)
            }
    }


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun postCryptoCoinList(cryptoCoinUIModelList: List<CryptoCoinUIModel>) {
        _cryptoList.value = cryptoCoinUIModelList
        _filteredCryptoList.value = cryptoList.value
    }

    fun onCryptoCoinClicked() {
        navigateToCryptoCoinDetailFragment()
    }

    fun filterCryptoCoinList(searchText: String) {
        if (searchText.isEmpty()) {
            resetCryptoCoinList()
        } else {
            _filteredCryptoList.value = cryptoList.value?.filter {
                (it.coinNameText.contains(searchText, ignoreCase = true)
                        || it.coinSymbolText.contains(searchText, ignoreCase = true))
            }
        }
    }

    private fun resetCryptoCoinList() {
        _filteredCryptoList.value = cryptoList.value
    }

    fun navigateToFavoriteCryptoCoinsFragment() {
        navigate(CryptoCoinListFragmentDirections.toCryptoCoinFavoritesFragment())
    }

    private fun navigateToCryptoCoinDetailFragment() {
        navigate(CryptoCoinListFragmentDirections.toCryptoCoinDetailFragment())
    }

}