package com.bkarakoca.cryptocurrencyapp.scene.login.register

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseFragment
import com.bkarakoca.cryptocurrencyapp.databinding.FragmentRegisterBinding
import com.bkarakoca.cryptocurrencyapp.internal.extension.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {
    override val layoutId: Int = R.layout.fragment_register

    override fun initViews() {
        binder.inputViewEmail.setInputTypeEmail()
        binder.inputViewPassword.setInputTypePassword()
        binder.inputViewPasswordConfirm.setInputTypePassword()
    }

    override fun setListeners() {
        binder.btRegister.setOnClickListener {
            viewModel.onRegisterClicked(
                binder.inputViewEmail.getText(),
                binder.inputViewPassword.getText(),
                binder.inputViewPasswordConfirm.getText()
            )
        }
    }

    override fun setReceivers() {
        observe(viewModel.register) { isRegisterSuccess ->
            if (isRegisterSuccess) {
                viewModel.onRegisterSuccess()
            }
        }
    }
}