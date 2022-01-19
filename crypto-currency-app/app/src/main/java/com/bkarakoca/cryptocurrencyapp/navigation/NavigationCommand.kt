package com.bkarakoca.cryptocurrencyapp.navigation

import androidx.navigation.NavDirections
import com.bkarakoca.cryptocurrencyapp.internal.popup.PopupListener
import com.bkarakoca.cryptocurrencyapp.internal.popup.PopupModel

sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    data class Popup(val model: PopupModel, val listener: PopupListener? = null) :
        NavigationCommand()

    object Back : NavigationCommand()
}
