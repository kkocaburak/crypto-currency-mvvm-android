package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptofavorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bkarakoca.cryptocurrencyapp.base.BaseViewModel
import com.bkarakoca.cryptocurrencyapp.domain.crypto.GetFavoriteCryptoCoinsUseCase
import com.bkarakoca.cryptocurrencyapp.internal.extension.launch
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CryptoCoinFavoritesViewModel @Inject constructor(
    private val getFavoriteCryptoCoinsUseCase: GetFavoriteCryptoCoinsUseCase
): BaseViewModel() {

    private val _cryptoList = MutableLiveData<List<CryptoCoinUIModel>>()
    val cryptoList: LiveData<List<CryptoCoinUIModel>> get() = _cryptoList

    fun fetchFavoriteCryptoCoins() = launch {
        getFavoriteCryptoCoinsUseCase.execute(Unit)
            .catch {
                showErrorPopupWithBackAction(message = "favorites are empty")
            }
            .collect {
                _cryptoList.value = it
            }
    }
}