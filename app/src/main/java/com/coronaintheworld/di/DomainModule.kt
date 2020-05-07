package com.coronaintheworld.di

import com.coronaintheworld.repository.CountryRepository
import com.coronaintheworld.repository.NotificationRepository
import org.koin.dsl.module

val domainModule = module {

    factory {
        CountryRepository(
            covidApi = get(),
            responseHandler = get()
        )
    }

    factory {
        NotificationRepository(
            notificationDap = get()
        )
    }


}
