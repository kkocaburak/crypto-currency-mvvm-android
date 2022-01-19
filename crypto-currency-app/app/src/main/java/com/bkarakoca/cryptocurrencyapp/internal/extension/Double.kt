package com.bkarakoca.cryptocurrencyapp.internal.extension

import java.text.NumberFormat

fun Double.toPercentageString(): String {
    val defaultFormat = NumberFormat.getPercentInstance()
    defaultFormat.minimumFractionDigits = 1
    return defaultFormat.format(this)
}