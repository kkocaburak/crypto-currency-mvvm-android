package com.bkarakoca.cryptocurrencyapp.scene.cryptolist

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseListAdapter
import com.bkarakoca.cryptocurrencyapp.databinding.ItemCryptoCoinBinding
import com.bkarakoca.cryptocurrencyapp.internal.extension.executeAfter
import com.bkarakoca.cryptocurrencyapp.scene.cryptolist.model.CryptoCoinUIModel

class CryptoListAdapter : BaseListAdapter<ItemCryptoCoinBinding, CryptoCoinUIModel>() {

    override val layoutRes = R.layout.item_crypto_coin

    override fun bind(binding: ItemCryptoCoinBinding, item: CryptoCoinUIModel) {
        binding.executeAfter {
            coinItem = item
        }
    }
}