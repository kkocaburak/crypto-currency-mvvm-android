package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptodetail

import androidx.core.widget.doAfterTextChanged
import androidx.navigation.navGraphViewModels
import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseFragment
import com.bkarakoca.cryptocurrencyapp.databinding.FragmentCryptoCoinDetailBinding
import com.bkarakoca.cryptocurrencyapp.internal.extension.toSafeLong
import com.bkarakoca.cryptocurrencyapp.scene.crypto.CryptoSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoCoinDetailFragment :
    BaseFragment<CryptoCoinDetailViewModel, FragmentCryptoCoinDetailBinding>() {

    override val layoutId: Int = R.layout.fragment_crypto_coin_detail

    private val cryptoSharedViewModel: CryptoSharedViewModel by navGraphViewModels(R.id.nav_graph_crypto)

    override fun initialize() {
        fetchCryptoCoinDetail()
    }

    override fun setListeners() {
        binder.etRefreshRate.doAfterTextChanged { editable ->
            viewModel.updateRefreshRateForCoinPrice(editable?.toSafeLong())
        }
    }

    private fun fetchCryptoCoinDetail() {
        viewModel.fetchCryptoCoinDetail(cryptoSharedViewModel.cryptoCoinUIModel.id)
    }
}