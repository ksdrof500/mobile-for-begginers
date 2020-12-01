package com.coronaintheworld.data.remote

import com.coronaintheworld.data.mapper.CountryMapper
import com.coronaintheworld.domain.entity.Country
import com.coronaintheworld.domain.entity.DetailCountry

class CountryDataSource(private val covidApi: CovidApi) : CountrySource {
    override suspend fun getCountries(): List<Country> {
        return CountryMapper.map(covidApi.getCountries())
    }

    override suspend fun getDataByCountry(slug: String): DetailCountry {
        return covidApi.getDataByCountry(slug, "2020-05-05T00:00:00Z").first()
    }
}
