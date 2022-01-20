package com.bkarakoca.cryptocurrencyapp.scene.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.bkarakoca.cryptocurrencyapp.R
import com.bkarakoca.cryptocurrencyapp.base.BaseBindingActivity
import com.bkarakoca.cryptocurrencyapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId get() = R.layout.activity_main

    val navController: NavController by lazy { findNavController(R.id.main_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder.viewModel = viewModel
        binder.lifecycleOwner = this
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
