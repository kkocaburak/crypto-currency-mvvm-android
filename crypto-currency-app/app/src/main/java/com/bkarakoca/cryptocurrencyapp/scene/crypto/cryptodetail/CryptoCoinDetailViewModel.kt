package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseViewModel
import com.bkarakoca.cryptocurrencyapp.domain.crypto.GetCryptoCoinDetailUseCase
import com.bkarakoca.cryptocurrencyapp.domain.crypto.SetFavoriteCryptoCoinUseCase
import com.bkarakoca.cryptocurrencyapp.internal.extension.launch
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptodetail.model.CryptoCoinDetailUIModel
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CryptoCoinDetailViewModel @Inject constructor(
    private val getCryptoCoinDetailUseCase: GetCryptoCoinDetailUseCase,
    private val setFavoriteCryptoCoinUseCase: SetFavoriteCryptoCoinUseCase
) : BaseViewModel() {

    private val _cryptoCoinDetail = MutableLiveData<CryptoCoinDetailUIModel>()
    val cryptoCoinDetail: LiveData<CryptoCoinDetailUIModel> get() = _cryptoCoinDetail

    private val _cryptoCoinFavoriteResponse = MutableLiveData<Boolean>()
    val cryptoCoinFavoriteResponse: LiveData<Boolean> get() = _cryptoCoinFavoriteResponse

    private var coinPriceRefreshDuration: Long? = null // TODO : use this for repeating coin price update

    fun fetchCryptoCoinDetail(cryptoCoinId: String) = launch {
        getCryptoCoinDetailUseCase.execute(
            GetCryptoCoinDetailUseCase.Params(cryptoCoinId)
        ).collect {
            _cryptoCoinDetail.value = it
        }
    }

    fun postFavoriteCryptoCoin(cryptoCoinUIModel: CryptoCoinUIModel) = launch {
        setFavoriteCryptoCoinUseCase.execute(
            SetFavoriteCryptoCoinUseCase.Params(cryptoCoinUIModel)
        ).collect {
            if (it.success) {
                showErrorPopupWithBackAction(getString(R.string.crypto_favorite_success))
            }
        }
    }

    fun updateRefreshRateForCoinPrice(durationInMS: Long?) {
        durationInMS?.let {
            this.coinPriceRefreshDuration = it
        }
    }

}