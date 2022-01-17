package com.bkarakoca.cryptocurrencyapp.internal.injection.module

import com.bkarakoca.cryptocurrencyapp.scene.cryptolist.CryptoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeCryptoListFragment(): CryptoListFragment

}
