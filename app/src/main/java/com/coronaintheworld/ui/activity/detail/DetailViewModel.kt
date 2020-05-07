package com.coronaintheworld.ui.activity.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.coronaintheworld.local.FavoritePreferences
import com.coronaintheworld.remote.common.ViewState
import com.coronaintheworld.repository.CountryRepository

class DetailViewModel(
    val favoritePreferences: FavoritePreferences,
    val countryRepository: CountryRepository
) : ViewModel() {

    private val slug = MutableLiveData<String>()

    var detail = slug.switchMap {
        liveData {
            emit(ViewState.loading())
            emit(countryRepository.getDataByCountry(it))
        }
    }

    fun getDetail(slugCountry: String) {
        slug.value = slugCountry
    }

    fun savePreferences(slug: String) {
        favoritePreferences.saveCountry(slug)
    }
}