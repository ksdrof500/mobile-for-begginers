package com.coronaintheworld.domain.usecase

import com.coronaintheworld.domain.repository.CountryRepository

class CountriesByDateUseCase(private val countryRepository: CountryRepository) {

    suspend operator fun invoke(slug: String) = countryRepository.getDataByCountry(slug)
}
