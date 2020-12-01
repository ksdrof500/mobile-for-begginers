package com.coronaintheworld.data.repository

import com.coronaintheworld.data.remote.CountrySource
import com.coronaintheworld.domain.repository.CountryRepository

class CountryDataRepository(private val countrySource: CountrySource) : CountryRepository {
    override suspend fun getCountries() = countrySource.getCountries()

    override suspend fun getDataByCountry(slug: String) = countrySource.getDataByCountry(slug)
}
