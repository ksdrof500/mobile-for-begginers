package com.coronaintheworld.domain

import com.coronaintheworld.domain.entity.DetailCountry
import com.coronaintheworld.domain.repository.CountryRepository
import com.coronaintheworld.domain.usecase.CountriesByDateUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountriesByDateUseCaseTest {

    private lateinit var useCase: CountriesByDateUseCase
    private lateinit var countryRepository: CountryRepository
    private val slug = "brazil"

    @ExperimentalCoroutinesApi
    @Before
    fun beforeEachTest() {
        countryRepository = mock()
        useCase = CountriesByDateUseCase(countryRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should call use case times`() =
        runBlockingTest {
            val detailCountry = DetailCountry(1, 2, 3, 4)
            whenever(useCase(slug)).thenReturn(detailCountry)

            assertEquals(useCase.invoke(slug), detailCountry)
        }
}
