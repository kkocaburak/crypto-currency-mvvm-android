package com.bkarakoca.cryptocurrencyapp.internal.injection.component

import com.bkarakoca.cryptocurrencyapp.internal.DaggerApplication
import com.bkarakoca.cryptocurrencyapp.internal.injection.module.ActivitiesModule
import com.bkarakoca.cryptocurrencyapp.internal.injection.module.AppModule
import com.bkarakoca.cryptocurrencyapp.internal.injection.module.FragmentsModule
import com.bkarakoca.cryptocurrencyapp.internal.injection.module.NetworkModule
import com.bkarakoca.cryptocurrencyapp.internal.injection.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivitiesModule::class,
        FragmentsModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
internal interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()
}