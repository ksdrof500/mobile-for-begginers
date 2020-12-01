package com.coronaintheworld.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.coronaintheworld.common.ViewState
import com.coronaintheworld.domain.entity.DetailCountry
import com.coronaintheworld.local.FavoritePreferences
import com.coronaintheworld.ui.activity.detail.DetailViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private lateinit var countryRepository: CountryRepository
    private lateinit var favoritePreferences: FavoritePreferences
    private lateinit var detailObserver: Observer<ViewState<DetailCountry>>
    private val slug = "brazil"

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        countryRepository = mock()
        detailObserver = mock()
        favoritePreferences = mock()
        viewModel = DetailViewModel(favoritePreferences, countryRepository)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when detail is called with slug existing, then observer is updated with success`() =
        runBlockingTest {
            // Given
            val success = ViewState.success(data = Any())
            whenever(countryRepository.getDataByCountry(slug)).thenReturn(success as ViewState<*>?)

            // When
            viewModel.detail.observeForever(detailObserver)
            viewModel.getDetail(slug)

            // Then
            verify(countryRepository).getDataByCountry(slug)
            verify(detailObserver).onChanged(ViewState.loading())
            verify(detailObserver).onChanged(success)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when detail is called with slug not existing, then observer is updated with failure`() =
        runBlockingTest {
            // Given
            val errorResource = ViewState.error("Unauthorised", null)
            whenever(countryRepository.getDataByCountry(slug)).thenReturn(errorResource)

            // When
            viewModel.detail.observeForever(detailObserver)
            viewModel.getDetail(slug)

            // Then
            verify(countryRepository).getDataByCountry(slug)
            verify(detailObserver).onChanged(ViewState.loading())
            verify(detailObserver).onChanged(errorResource)
        }
}
