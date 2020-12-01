package com.coronaintheworld.ui.activity.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.coronaintheworld.common.ResponseHandler
import com.coronaintheworld.common.ViewState
import com.coronaintheworld.domain.usecase.CountriesByDateUseCase
import com.coronaintheworld.local.FavoritePreferences
import com.coronaintheworld.mapper.DetailCountryUIModelMapper

class DetailViewModel(
    val favoritePreferences: FavoritePreferences,
    val countriesByDateUseCase: CountriesByDateUseCase,
    val context: Context,
    val responseHandler: ResponseHandler
) : ViewModel() {

    private val slug = MutableLiveData<String>()

    var detail = slug.switchMap {
        liveData {
            emit(ViewState.loading())
            try {

                countriesByDateUseCase(it)?.let { detail ->
                    emit(
                        responseHandler.handleSuccess(
                            DetailCountryUIModelMapper.map(context, detail)
                        )
                    )
                } ?: emit(responseHandler.handleException(Exception()))
            } catch (e: Exception) {
                emit(responseHandler.handleException(e))
            }
        }
    }

    fun getDetail(slugCountry: String) {
        slug.value = slugCountry
    }

    fun savePreferences(slug: String) {
        favoritePreferences.saveCountry(slug)
    }
}
