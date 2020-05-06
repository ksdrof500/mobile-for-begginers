package com.coronaintheworld.repository

import com.coronaintheworld.remote.CovidApi
import com.coronaintheworld.remote.common.ResponseHandler
import com.coronaintheworld.remote.common.ViewState
import com.coronaintheworld.remote.dto.Country
import com.coronaintheworld.remote.dto.DetailCountry
import java.text.SimpleDateFormat
import java.util.*

class CountryRepository(
    val covidApi: CovidApi,
    val responseHandler: ResponseHandler
) {

    suspend fun getCountries(): ViewState<List<Country>> {
        return try {
            val response = covidApi.getCountries().sortedBy { it.nameCountry }
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getDataByCountry(slug: String): ViewState<DetailCountry> {
        return try {
            val response = covidApi.getDataByCountry(slug, formatDateYesterday()).first()
            responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    private fun formatDateYesterday(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val date = Calendar.getInstance()
        date.add(Calendar.DAY_OF_YEAR, -1)
        return inputFormat.format(date.time)
    }
}