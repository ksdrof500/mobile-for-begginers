package com.coronaintheworld.ui.home

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

//    private lateinit var viewModel: HomeViewModel
//    private lateinit var countryRepository: CountryRepository
//    private lateinit var countryObserver: Observer<ViewState<List<Country>>>
//
//    @Rule
//    @JvmField
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    @ExperimentalCoroutinesApi
//    private val testDispatcher = TestCoroutineDispatcher()
//
//    @ExperimentalCoroutinesApi
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(testDispatcher)
//        countryRepository = mock()
//        countryObserver = mock()
//        viewModel = HomeViewModel(countryRepository)
//    }
//
//    @ExperimentalCoroutinesApi
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `when countries is called with success, then observer is updated with success`() =
//        runBlockingTest {
//            // Given
//            val success = ViewState.success(data = Any())
//            whenever(countryRepository.getCountries()).thenReturn(success as ViewState<*>?)
//
//            // When
//            viewModel.countries.observeForever(countryObserver)
//            viewModel.countries
//
//            // Then
//            verify(countryRepository).getCountries()
//            verify(countryObserver).onChanged(ViewState.loading())
//            verify(countryObserver).onChanged(success)
//        }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `when countries is called with error, then observer is updated with failure`() =
//        runBlockingTest {
//            // Given
//            val errorResource = ViewState.error("Unauthorised", null)
//            whenever(countryRepository.getCountries()).thenReturn(errorResource)
//
//            // When
//            viewModel.countries.observeForever(countryObserver)
//            viewModel.countries
//
//            // Then
//            verify(countryRepository).getCountries()
//            verify(countryObserver).onChanged(ViewState.loading())
//            verify(countryObserver).onChanged(errorResource)
//        }
}
