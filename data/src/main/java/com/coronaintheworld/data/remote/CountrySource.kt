package com.coronaintheworld.data.remote

import com.coronaintheworld.domain.entity.Country
import com.coronaintheworld.domain.entity.DetailCountry

interface CountrySource {

    suspend fun getCountries(): List<Country>
    suspend fun getDataByCountry(slug: String): DetailCountry
}
