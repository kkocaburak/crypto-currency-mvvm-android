package com.bkarakoca.cryptocurrencyapp.base

interface ListAdapterItem {
    val id: Long

    override fun equals(other: Any?): Boolean
}
