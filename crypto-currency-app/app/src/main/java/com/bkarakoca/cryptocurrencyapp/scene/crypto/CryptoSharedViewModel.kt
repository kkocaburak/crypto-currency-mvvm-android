package com.bkarakoca.cryptocurrencyapp.scene.crypto

import com.bkarakoca.cryptocurrencyapp.base.BaseViewModel
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoSharedViewModel @Inject constructor() : BaseViewModel() {

    lateinit var cryptoCoinUIModel: CryptoCoinUIModel
}