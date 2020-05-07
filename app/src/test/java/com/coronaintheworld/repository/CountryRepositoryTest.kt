package com.coronaintheworld.repository

import com.coronaintheworld.remote.CovidApi
import com.coronaintheworld.remote.common.ResponseHandler
import com.coronaintheworld.remote.common.ViewState
import com.coronaintheworld.remote.dto.Country
import com.coronaintheworld.remote.dto.DetailCountry
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class CountryRepositoryTest {
    private val responseHandler = ResponseHandler()
    private lateinit var api: CovidApi
    private lateinit var repository: CountryRepository


    @Before
    fun setUp() {
        api = mock()
        repository = CountryRepository(
            api,
            responseHandler
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getCountry when is success requested, then countries is returned`() =
        runBlockingTest {

            val successResponse = listOf(Country("name", "slug", "sigla"))
            val success = ViewState.success(data = successResponse)

            whenever(api.getCountries()).thenReturn(successResponse)

            assertEquals(success, repository.getCountries())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getCountry when  is error requested, then error is returned`() =
        runBlockingTest {
            val errorResponse = ViewState.error("Unauthorised", null)
            val mockException: HttpException = mock()

            whenever(mockException.code()).thenReturn(401)
            whenever(api.getCountries()).thenThrow(mockException)

            assertEquals(errorResponse, repository.getCountries())
        }


    @ExperimentalCoroutinesApi
    @Test
    fun `test getDetail when is valid slug is requested, then detail is returned`() =
        runBlockingTest {

            val slug = "brazil"
            val successResponse = listOf(DetailCountry(1, 2, 3, 4))
            val success = ViewState.success(data = successResponse)

            whenever(api.getDataByCountry(eq(slug), anyString())).thenReturn(successResponse)

            repository.getDataByCountry(slug).run {
                assertEquals(success.status, status)
                assertEquals(success.data?.firstOrNull(), data)
            }
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `test getDetail when invalid slug is requested, then error is returned`() =
        runBlockingTest {
            val slug = "error"
            val errorResponse = ViewState.error("Unauthorised", null)
            val mockException: HttpException = mock()

            whenever(mockException.code()).thenReturn(401)
            whenever(api.getDataByCountry(eq(slug), anyString())).thenThrow(mockException)

            assertEquals(errorResponse, repository.getDataByCountry(slug))
        }
}