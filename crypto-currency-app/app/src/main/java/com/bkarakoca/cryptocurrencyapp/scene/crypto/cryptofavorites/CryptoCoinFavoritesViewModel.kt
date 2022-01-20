package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptofavorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bkarakoca.cryptocurrencyapp.base.BaseViewModel
import com.bkarakoca.cryptocurrencyapp.domain.crypto.GetFavoriteCryptoCoinsUseCase
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoCoinFavoritesViewModel @Inject constructor(
    private val getFavoriteCryptoCoinsUseCase: GetFavoriteCryptoCoinsUseCase
): BaseViewModel() {

    private val _cryptoList = MutableLiveData<List<CryptoCoinUIModel>>()
    val cryptoList: LiveData<List<CryptoCoinUIModel>> get() = _cryptoList

    fun fetchFavoriteCryptoCoins() = viewModelScope.launch {
        getFavoriteCryptoCoinsUseCase.execute(Unit)
            .catch { t ->
                handleException(t)
            }.collect {
                if (it == null) {
                    showPopup(message = "favorites are empty")
                }
                _cryptoList.value = it
            }
    }
}