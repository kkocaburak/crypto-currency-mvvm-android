package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist

import androidx.core.widget.doAfterTextChanged
import androidx.navigation.navGraphViewModels
import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseFragment
import com.bkarakoca.cryptocurrencyapp.databinding.FragmentCryptoListBinding
import com.bkarakoca.cryptocurrencyapp.internal.extension.observe
import com.bkarakoca.cryptocurrencyapp.scene.crypto.CryptoSharedViewModel
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoCoinListFragment: BaseFragment<CryptoCoinListViewModel, FragmentCryptoListBinding>(), CryptoCoinListAdapter.CryptoCoinOnClickListener {

    override val layoutId: Int = R.layout.fragment_crypto_list

    private val cryptoSharedViewModel: CryptoSharedViewModel by navGraphViewModels(R.id.nav_graph_crypto)

    var cryptoListAdapter = CryptoCoinListAdapter()

    override fun initialize() {
        viewModel.fetchCryptoCoinList()
    }

    override fun initViews() {
        binder.rvCryptoCoin.apply {
            adapter = cryptoListAdapter.apply {
                setClickListener(this@CryptoCoinListFragment)
            }
            setHasFixedSize(true)
        }
    }

    override fun setListeners() {
        binder.etSearch.doAfterTextChanged { editable ->
            viewModel.filterCryptoCoinList(editable.toString())
        }

        binder.btFavoriteCryptoCoins.setOnClickListener {
            viewModel.navigateToFavoriteCryptoCoinsFragment()
        }
    }

    override fun setReceivers() {
        observe(viewModel.filteredCryptoList) {
            cryptoListAdapter.submitList(it)
        }
    }

    override fun onCryptoCoinClicked(cryptoCoinUIModel: CryptoCoinUIModel) {
        cryptoSharedViewModel.cryptoCoinUIModel = cryptoCoinUIModel
        viewModel.onCryptoCoinClicked()
    }
}