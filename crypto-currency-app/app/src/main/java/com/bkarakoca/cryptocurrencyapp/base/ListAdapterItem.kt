package com.bkarakoca.cryptocurrencyapp.base

interface ListAdapterItem {
    val id: String?

    override fun equals(other: Any?): Boolean
}
