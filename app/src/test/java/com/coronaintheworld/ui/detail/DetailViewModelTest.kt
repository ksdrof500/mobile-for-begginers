package com.coronaintheworld.ui.detail

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.coronaintheworld.R
import com.coronaintheworld.common.ResponseHandler
import com.coronaintheworld.common.ViewState
import com.coronaintheworld.domain.entity.DetailCountry
import com.coronaintheworld.domain.usecase.CountriesByDateUseCase
import com.coronaintheworld.local.FavoritePreferences
import com.coronaintheworld.model.DetailCountryUiModel
import com.coronaintheworld.ui.activity.detail.DetailViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
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
    private lateinit var countriesByDateUseCase: CountriesByDateUseCase
    private lateinit var favoritePreferences: FavoritePreferences
    private lateinit var detailObserver: Observer<ViewState<DetailCountryUiModel>>
    private lateinit var context: Context
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
        countriesByDateUseCase = mock()
        detailObserver = mock()
        favoritePreferences = mock()
        context = mock()
        viewModel = DetailViewModel(favoritePreferences, countriesByDateUseCase, context, ResponseHandler())
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
            val detail = DetailCountry(1, 2, 3, 4)
            val detailUi = DetailCountryUiModel(
                "Confirmados até o momento: 1",
                "Mortos até o momento: 2",
                "Curados até o momento: 3",
                "Ativos até o momento: 4"
            )
            val success = ViewState.success(data = detailUi)

            // Given
            whenever(context.getString(R.string.confirmed, detail.confirmed)).thenReturn("Confirmados até o momento: 1")
            whenever(context.getString(R.string.death, detail.deaths)).thenReturn("Mortos até o momento: 2")
            whenever(context.getString(R.string.recovered, detail.recovered)).thenReturn("Curados até o momento: 3")
            whenever(context.getString(R.string.active, detail.active)).thenReturn("Ativos até o momento: 4")
            whenever(countriesByDateUseCase(slug)).thenReturn(detail)

            // When
            viewModel.detail.observeForever(detailObserver)
            viewModel.getDetail(slug)

            // Then
            verify(countriesByDateUseCase, times(1)).invoke(slug)
            verify(detailObserver).onChanged(ViewState.loading())
            verify(detailObserver).onChanged(success)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `when detail is called with slug not existing, then observer is updated with failure`() =
        runBlockingTest {
            // Given
            val errorResource = ViewState.error("Something went wrong", null)
            whenever(countriesByDateUseCase(slug)).thenReturn(null)

            // When
            viewModel.detail.observeForever(detailObserver)
            viewModel.getDetail(slug)

            // Then
            verify(countriesByDateUseCase, times(1)).invoke(slug)
            verify(detailObserver).onChanged(ViewState.loading())
            verify(detailObserver).onChanged(errorResource)
        }
}
