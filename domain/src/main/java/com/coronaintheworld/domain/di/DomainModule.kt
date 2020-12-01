package com.coronaintheworld.domain.di

import com.coronaintheworld.domain.usecase.AuthUseCase
import com.coronaintheworld.domain.usecase.CountriesByDateUseCase
import com.coronaintheworld.domain.usecase.CountriesUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        AuthUseCase(
            authRepository = get()
        )
    }

    factory {
        CountriesUseCase(
            countryRepository = get()
        )
    }

    factory {
        CountriesByDateUseCase(
            countryRepository = get()
        )
    }
}
