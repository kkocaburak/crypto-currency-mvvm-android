package com.bkarakoca.cryptocurrencyapp.internal.widget

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bkarakoca.cryptocurrencyapp.databinding.ViewUserInputBinding


class UserInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    var binding: ViewUserInputBinding =
        ViewUserInputBinding.inflate(LayoutInflater.from(context), this, true)

    fun setInputTypeEmail() {
        binding.etUserInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    fun setInputTypePassword() {
        binding.etUserInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

    }

    fun getText() = binding.etUserInput.text.toString()
}