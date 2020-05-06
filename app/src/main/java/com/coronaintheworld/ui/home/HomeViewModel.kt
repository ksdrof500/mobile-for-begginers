package com.coronaintheworld.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.coronaintheworld.remote.common.ViewState
import com.coronaintheworld.repository.CountryRepository

class HomeViewModel(val countryRepository: CountryRepository) : ViewModel() {

    val countries  = liveData {
        emit(ViewState.loading())
        emit(countryRepository.getCountries())
    }
}