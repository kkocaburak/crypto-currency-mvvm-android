package com.bkarakoca.cryptocurrencyapp.internal.databinding

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseListAdapter
import com.bkarakoca.cryptocurrencyapp.base.ListAdapterItem
import com.bkarakoca.cryptocurrencyapp.internal.extension.loadImage
import com.bkarakoca.cryptocurrencyapp.internal.widget.UserInputView

@BindingAdapter("hideIfNull")
fun setVisible(view: View, obj: Any?) {
    view.visibility = if (obj == null) {
        View.GONE
    } else if (obj is String && obj.isBlank()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("visibleIf")
fun visibleIf(view: View, shouldVisible: Boolean) {
    view.visibility = if (shouldVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("imageFromUrl", "placeholderRes", "errorRes", requireAll = false)
fun setImage(
    view: ImageView,
    url: String?,
    @DrawableRes placeholderRes: Int?,
    @DrawableRes errorRes: Int?
) {
    val defaultDrawable = R.drawable.ic_launcher_background

    view.loadImage(
        url,
        placeholderRes ?: defaultDrawable,
        errorRes ?: defaultDrawable
    )
}

@BindingAdapter("textResourceId")
fun setUserInputHeader(view: UserInputView, resId: Int) {
    view.binding.tvHeaderUserInput.text = view.context.getString(resId)
}