package com.coronaintheworld.di

import com.coronaintheworld.ui.activity.detail.DetailViewModel
import com.coronaintheworld.ui.activity.splash.SplashViewModel
import com.coronaintheworld.ui.home.HomeViewModel
import com.coronaintheworld.ui.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SplashViewModel(
            firebaseAuth = get()
        )
    }

    viewModel {
        HomeViewModel(
            countryRepository = get()
        )
    }

    viewModel {
        DetailViewModel(
            favoritePreferences = get(),
            countryRepository = get()
        )
    }

    viewModel {
        NotificationsViewModel(
            notificationRepository = get()
        )
    }

}