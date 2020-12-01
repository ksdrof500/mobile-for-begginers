package com.coronaintheworld.domain.repository

import com.coronaintheworld.domain.entity.Country
import com.coronaintheworld.domain.entity.DetailCountry

interface CountryRepository {

    suspend fun getCountries(): List<Country>?
    suspend fun getDataByCountry(slug: String): DetailCountry?
}
