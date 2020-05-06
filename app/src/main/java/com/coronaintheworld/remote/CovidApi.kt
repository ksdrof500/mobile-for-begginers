package com.coronaintheworld.remote

import com.coronaintheworld.remote.dto.Country
import com.coronaintheworld.remote.dto.DetailCountry
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {

    @GET("countries")
    suspend fun getCountries(): List<Country>

    @GET("live/country/{country-slug}/status/confirmed/date/{date}")
    suspend fun getDataByCountry(@Path("country-slug") slug: String,
                                 @Path("date") date: String): List<DetailCountry>
}