package com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseListAdapter
import com.bkarakoca.cryptocurrencyapp.databinding.ItemCryptoCoinBinding
import com.bkarakoca.cryptocurrencyapp.internal.extension.executeAfter
import com.bkarakoca.cryptocurrencyapp.scene.crypto.cryptolist.model.CryptoCoinUIModel

class CryptoCoinListAdapter : BaseListAdapter<ItemCryptoCoinBinding, CryptoCoinUIModel>() {

    interface CryptoCoinOnClickListener {
        fun onCryptoCoinClicked(cryptoCoinUIModel: CryptoCoinUIModel)
    }

    private var clickListener: CryptoCoinOnClickListener? = null

    override val layoutRes = R.layout.item_crypto_coin

    override fun bind(binding: ItemCryptoCoinBinding, item: CryptoCoinUIModel) {
        binding.executeAfter {
            coinItem = item
        }
        binding.clRoot.setOnClickListener {
            clickListener?.onCryptoCoinClicked(item)
        }
    }

    fun setClickListener(clickListener: CryptoCoinOnClickListener) {
        this.clickListener = clickListener
    }
}