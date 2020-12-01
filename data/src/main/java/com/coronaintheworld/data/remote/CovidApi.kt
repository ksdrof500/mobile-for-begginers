package com.coronaintheworld.data.remote

import com.coronaintheworld.domain.entity.Country
import com.coronaintheworld.domain.entity.DetailCountry
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {

    @GET("countries")
    suspend fun getCountries(): List<Country>?

    @GET("live/country/{country-slug}/status/confirmed/date/{date}")
    suspend fun getDataByCountry(
        @Path("country-slug") slug: String,
        @Path("date") date: String
    ): List<DetailCountry>?
}
