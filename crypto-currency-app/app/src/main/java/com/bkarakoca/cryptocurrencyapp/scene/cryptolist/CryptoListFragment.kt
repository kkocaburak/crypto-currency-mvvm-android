package com.bkarakoca.cryptocurrencyapp.scene.cryptolist

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseFragment
import com.bkarakoca.cryptocurrencyapp.databinding.FragmentCryptoListBinding
import com.bkarakoca.cryptocurrencyapp.internal.extension.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoListFragment: BaseFragment<CryptoListViewModel, FragmentCryptoListBinding>() {

    override val layoutId: Int = R.layout.fragment_crypto_list

    var cryptoListAdapter = CryptoListAdapter()

    override fun initialize() {
        setReceivers()
        viewModel.fetchCryptoCoinList()
    }

    private fun setReceivers() {
        observe(viewModel.cryptoList) {
            cryptoListAdapter.submitList(it)

            binder.rvCryptoCoin.apply {
                adapter = cryptoListAdapter
                setHasFixedSize(true)
            }
        }
    }

}