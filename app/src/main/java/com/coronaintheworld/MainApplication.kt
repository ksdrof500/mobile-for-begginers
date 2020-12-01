package com.coronaintheworld

import android.app.Application
import com.coronaintheworld.data.di.dataModules
import com.coronaintheworld.data.di.remoteModule
import com.coronaintheworld.data.di.repositoryModules
import com.coronaintheworld.di.commonModule
import com.coronaintheworld.di.localDataModule
import com.coronaintheworld.di.viewModelModule
import com.coronaintheworld.domain.di.useCaseModule
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
            modules(
                listOf(
                    localDataModule,
                    viewModelModule,
                    commonModule,
                    useCaseModule,
                    remoteModule,
                    dataModules,
                    repositoryModules
                )
            )
        }
    }

    private fun initFirebase() {
        FirebaseAnalytics.getInstance(this)
    }
}
