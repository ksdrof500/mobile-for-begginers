package com.coronaintheworld.domain.usecase

import com.coronaintheworld.domain.repository.CountryRepository

class CountriesUseCase(private val countryRepository: CountryRepository) {

    suspend operator fun invoke() = countryRepository.getCountries()
}
