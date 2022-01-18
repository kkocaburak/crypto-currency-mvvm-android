package com.bkarakoca.cryptocurrencyapp.scene.cryptolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bkarakoca.cryptocurrencyapp.base.BaseViewModel
import com.bkarakoca.cryptocurrencyapp.domain.crypto.GetCryptoListUseCase
import com.bkarakoca.cryptocurrencyapp.internal.util.Failure
import com.bkarakoca.cryptocurrencyapp.scene.cryptolist.model.CryptoCoinUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val cryptoListUseCase: GetCryptoListUseCase
) : BaseViewModel() {

    private val _cryptoList = MutableLiveData<List<CryptoCoinUIModel>>()
    val cryptoList: LiveData<List<CryptoCoinUIModel>> get() = _cryptoList

    fun fetchCryptoCoinList() = viewModelScope.launch {
        cryptoListUseCase.execute(Unit)
            .catch { failure ->
                handleFailure(failure as Failure)
            }.collect {
                postCryptoCoinList(it)
            }
    }

    private fun postCryptoCoinList(cryptoCoinUIModelList: List<CryptoCoinUIModel>) {
        _cryptoList.value = cryptoCoinUIModelList
    }

}