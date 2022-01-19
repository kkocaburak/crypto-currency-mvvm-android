package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bkarakoca.cryptocurrencyapp.base.BaseViewModel
import com.bkarakoca.cryptocurrencyapp.domain.crypto.GetCryptoCoinDetailUseCase
import com.bkarakoca.cryptocurrencyapp.internal.util.Failure
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptodetail.model.CryptoCoinDetailUIModel
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CryptoCoinDetailViewModel @Inject constructor(
    private val getCryptoCoinDetailUseCase: GetCryptoCoinDetailUseCase
) : BaseViewModel() {

    private val _cryptoDetail = MutableLiveData<CryptoCoinDetailUIModel>()
    val cryptoDetail: LiveData<CryptoCoinDetailUIModel> get() = _cryptoDetail

    private var coinPriceRefreshDuration: Long? = null // TODO : use this for repeating coin price update

    fun fetchCryptoCoinDetail(cryptoCoinId: String) = viewModelScope.launch {
        getCryptoCoinDetailUseCase.execute(
            GetCryptoCoinDetailUseCase.Params(cryptoCoinId)
        ).catch { failure ->
            handleFailure(failure as Failure)
        }.collect {
            _cryptoDetail.value = it
        }
    }

    fun updateRefreshRateForCoinPrice(durationInMS: Long?) {
        durationInMS?.let {
            this.coinPriceRefreshDuration = it
        }
    }

}