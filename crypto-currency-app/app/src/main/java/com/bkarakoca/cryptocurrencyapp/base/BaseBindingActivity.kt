package com.bkarakoca.cryptocurrencyapp.base

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bkarakoca.cryptocurrencyapp.internal.util.functional.lazyThreadSafetyNone

abstract class BaseBindingActivity<VM : BaseViewModel, B : ViewDataBinding> :
    BaseActivity<VM>() {

    protected val binder by lazyThreadSafetyNone<B> {
        DataBindingUtil.setContentView(this, layoutId)
    }

    @get:LayoutRes
    abstract val layoutId: Int
}
