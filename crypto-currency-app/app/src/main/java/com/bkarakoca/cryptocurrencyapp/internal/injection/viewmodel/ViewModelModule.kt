package com.bkarakoca.cryptocurrencyapp.internal.injection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bkarakoca.cryptocurrencyapp.scene.cryptolist.CryptoListViewModel
import com.bkarakoca.cryptocurrencyapp.scene.main.MainViewModel
import com.bkarakoca.cryptocurrencyapp.scene.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindsSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CryptoListViewModel::class)
    abstract fun bindsCryptoListViewModel(viewModel: CryptoListViewModel): ViewModel

}
