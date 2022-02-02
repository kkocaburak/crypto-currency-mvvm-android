package com.bkarakoca.cryptocurrencyapp.scene.login.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseViewModel
import com.bkarakoca.cryptocurrencyapp.domain.crypto.CreateCryptoListDocumentUseCase
import com.bkarakoca.cryptocurrencyapp.domain.login.RegisterUseCase
import com.bkarakoca.cryptocurrencyapp.internal.extension.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val createCryptoListDocumentUseCase: CreateCryptoListDocumentUseCase
) : BaseViewModel() {

    val registerEmailTextResId = R.string.login_user_mail
    val registerPasswordTextResId = R.string.login_user_password
    val registerPasswordConfirmTextResId = R.string.login_user_password_confirm

    private val _register = MutableLiveData<Boolean>()
    val register: LiveData<Boolean> get() = _register

    fun onRegisterClicked(emailText: String, password: String, passwordConfirm: String) {
        if (isPasswordValid(password, passwordConfirm)) {
            postRegister(emailText, password)
        } else {
            showPopup(getString(R.string.common_error_register_password_not_equal))
        }
    }

    private fun isPasswordValid(password: String, passwordConfirm: String) =
        password == passwordConfirm

    private fun postRegister(emailText: String, password: String) = launch {
        registerUseCase.execute(
            RegisterUseCase.Params(emailText, password)
        ).collect {
            createDocumentForUser(emailText)
        }
    }

    private suspend fun createDocumentForUser(email: String) {
        createCryptoListDocumentUseCase.execute(
            CreateCryptoListDocumentUseCase.Params(email)
        ).collect{
            _register.postValue(it.success)
        }
    }

    fun onRegisterSuccess() {
        navigate(RegisterFragmentDirections.toLoginFragment())
    }

}