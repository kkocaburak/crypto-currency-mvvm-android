package com.bkarakoca.cryptocurrencyapp.scene.splash

import android.os.Bundle
import com.bkarakoca.cryptocurrencyapp.base.BaseActivity
import com.bkarakoca.cryptocurrencyapp.scene.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startMainActivity()
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}
