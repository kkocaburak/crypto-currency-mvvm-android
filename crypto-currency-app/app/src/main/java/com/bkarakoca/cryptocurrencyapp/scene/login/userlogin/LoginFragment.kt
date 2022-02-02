package com.bkarakoca.cryptocurrencyapp.scene.login.userlogin

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseFragment
import com.bkarakoca.cryptocurrencyapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    override val layoutId: Int = R.layout.fragment_login

    override fun initViews() {
        binder.inputViewEmail.setInputTypeEmail()
        binder.inputViewPassword.setInputTypePassword()
    }

    override fun setListeners() {
        binder.btLogin.setOnClickListener {
            viewModel.onLoginClicked(binder.inputViewEmail.getText(), binder.inputViewPassword.getText())
        }

        binder.btRegister.setOnClickListener {
            viewModel.onRegisterClicked()
        }
    }
}