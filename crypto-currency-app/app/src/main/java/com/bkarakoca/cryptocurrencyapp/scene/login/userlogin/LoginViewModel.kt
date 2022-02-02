package com.bkarakoca.cryptocurrencyapp.scene.login.userlogin

import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.application.UserManager
import com.bkarakoca.cryptocurrencyapp.base.BaseViewModel
import com.bkarakoca.cryptocurrencyapp.domain.login.LoginUseCase
import com.bkarakoca.cryptocurrencyapp.internal.extension.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val loginEmailTextResId = R.string.login_user_mail
    val loginPasswordTextResId = R.string.login_user_password

    fun onLoginClicked(emailText: String, passwordText: String) {
        postLogin(emailText, passwordText)
    }

    fun onRegisterClicked() {
        navigateToRegisterFragment()
    }

    private fun postLogin(emailText: String, passwordText: String) = launch {
        loginUseCase.execute(
            LoginUseCase.Params(emailText, passwordText)
        ).collect {
            it.email?.let { email ->
                UserManager.userEmail = email
                navigateToCryptoListFragment()
            } ?: showPopup(getString(R.string.common_error_login))
        }
    }

    private fun navigateToRegisterFragment() {
        navigate(LoginFragmentDirections.toRegisterFragment())
    }

    private fun navigateToCryptoListFragment() {
        navigate(LoginFragmentDirections.toNavGraphCrypto())
    }

}