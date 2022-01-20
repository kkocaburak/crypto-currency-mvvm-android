package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptofavorites

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseFragment
import com.bkarakoca.cryptocurrencyapp.databinding.FragmentCryptoCoinFavoritesBinding
import com.bkarakoca.cryptocurrencyapp.internal.extension.observe
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.CryptoCoinListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoCoinFavoritesFragment: BaseFragment<CryptoCoinFavoritesViewModel, FragmentCryptoCoinFavoritesBinding>() {
    override val layoutId: Int = R.layout.fragment_crypto_coin_favorites

    var cryptoListAdapter = CryptoCoinListAdapter()

    override fun initialize() {
        viewModel.fetchFavoriteCryptoCoins()
    }

    override fun initViews() {
        binder.rvCryptoCoinFavorites.apply {
            adapter = cryptoListAdapter
            setHasFixedSize(true)
        }
    }

    override fun setReceivers() {
        observe(viewModel.cryptoList) {
            cryptoListAdapter.submitList(it)
        }
    }
}