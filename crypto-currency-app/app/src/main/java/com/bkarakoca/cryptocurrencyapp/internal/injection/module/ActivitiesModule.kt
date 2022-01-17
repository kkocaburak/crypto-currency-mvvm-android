package com.bkarakoca.cryptocurrencyapp.internal.injection.module

import com.bkarakoca.cryptocurrencyapp.internal.injection.scope.MainScope
import com.bkarakoca.cryptocurrencyapp.scene.main.MainActivity
import com.bkarakoca.cryptocurrencyapp.scene.main.MainModule
import com.bkarakoca.cryptocurrencyapp.scene.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    internal abstract fun contributeSplashActivity(): SplashActivity
}
