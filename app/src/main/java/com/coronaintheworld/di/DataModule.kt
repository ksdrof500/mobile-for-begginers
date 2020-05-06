package com.coronaintheworld.di

import com.coronaintheworld.local.FavoritePreferences
import com.coronaintheworld.local.NotificationDatabase
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { FavoritePreferences(androidContext()) }
    single { setupFirebaseAuth() }
    single { NotificationDatabase.getInstance(androidContext()) }
}

private fun setupFirebaseAuth() = FirebaseAuth.getInstance()