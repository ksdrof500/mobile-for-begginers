package com.coronaintheworld.data.remote

import com.coronaintheworld.domain.entity.Country
import com.coronaintheworld.domain.entity.DetailCountry
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class CountryDataSourceTest {

    private lateinit var countryDataSource: CountryDataSource
    private lateinit var countrySource: CountrySource
    private lateinit var covidApi: CovidApi

    @ExperimentalCoroutinesApi
    @Before
    fun beforeEachTest() {
        covidApi = mock()
        countrySource = mock()
        countryDataSource = CountryDataSource(covidApi)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getCountry when is success requested, then countries is returned`() =
        runBlockingTest {

            val successResponse = listOf(Country("name", "slug", "sigla"))

            whenever(covidApi.getCountries()).thenReturn(successResponse)
            whenever(countrySource.getCountries()).thenReturn(successResponse)
            whenever(countryDataSource.getCountries()).thenReturn(successResponse)

            assertEquals(successResponse, countrySource.getCountries())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getCountry when  is error requested, then error is returned`() =
        runBlockingTest {
            val mockException: HttpException = mock()

            whenever(mockException.code()).thenReturn(401)
            whenever(covidApi.getCountries()).thenReturn(null)
            whenever(countryDataSource.getCountries()).thenReturn(null)

            assertEquals(null, countrySource.getCountries())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getDetail when is valid slug is requested, then detail is returned`() =
        runBlockingTest {

            val slug = "brazil"
            val successResponse = DetailCountry(1, 2, 3, 4)

            whenever(covidApi.getDataByCountry(slug, "2020-05-05T00:00:00Z")).thenReturn(listOf(successResponse))
            whenever(countrySource.getDataByCountry(slug)).thenReturn(successResponse)
            whenever(countryDataSource.getDataByCountry(slug)).thenReturn(successResponse)

            countrySource.getDataByCountry(slug).run {
                assertEquals(successResponse, this)
            }
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getDetail when invalid slug is requested, then error is returned`() =
        runBlockingTest {
            val slug = "error"
            val mockException: HttpException = mock()

            whenever(mockException.code()).thenReturn(401)
            whenever(covidApi.getDataByCountry(slug, "2020-05-05T00:00:00Z")).thenReturn(null)
            whenever(countryDataSource.getDataByCountry(slug)).thenReturn(null)

            assertEquals(null, countrySource.getDataByCountry(slug))
        }
}
