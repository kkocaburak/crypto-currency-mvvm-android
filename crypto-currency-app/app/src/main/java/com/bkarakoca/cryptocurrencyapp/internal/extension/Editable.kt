package com.bkarakoca.cryptocurrencyapp.internal.extension

import android.text.Editable

fun Editable.toSafeLong(): Long? {
    return this.toString().toLongOrNull()
}