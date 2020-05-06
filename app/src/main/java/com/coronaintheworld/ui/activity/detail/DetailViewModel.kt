package com.coronaintheworld.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.coronaintheworld.local.FavoritePreferences
import com.coronaintheworld.remote.common.ViewState
import com.coronaintheworld.remote.dto.DetailCountry
import com.coronaintheworld.repository.CountryRepository

class DetailViewModel(val favoritePreferences: FavoritePreferences,
                      val countryRepository: CountryRepository) : ViewModel() {

    lateinit var detail: LiveData<ViewState<DetailCountry>>

    fun getDetail(slug: String) {
         detail = liveData {
            emit(ViewState.loading())
            emit(countryRepository.getDataByCountry(slug))
        }
    }

    fun savePreferences(slug: String) {
        favoritePreferences.saveCountry(slug)
    }
}