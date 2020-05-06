package com.coronaintheworld

import android.app.Application
import com.coronaintheworld.di.dataModule
import com.coronaintheworld.di.domainModule
import com.coronaintheworld.di.networkModule
import com.coronaintheworld.di.viewModelModule
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initFirebase()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(dataModule, viewModelModule, domainModule, networkModule))
        }
    }

    private fun initFirebase() {
        FirebaseAnalytics.getInstance(this)
    }

}