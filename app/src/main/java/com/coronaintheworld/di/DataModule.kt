package com.coronaintheworld.di

import com.coronaintheworld.local.FavoritePreferences
import com.coronaintheworld.local.NotificationDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single { FavoritePreferences(androidContext()) }
    single { NotificationDatabase.getInstance(androidContext()).notificationDao }
}
