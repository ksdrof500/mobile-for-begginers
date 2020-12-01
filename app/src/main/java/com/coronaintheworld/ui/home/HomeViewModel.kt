package com.coronaintheworld.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.coronaintheworld.common.ResponseHandler
import com.coronaintheworld.common.ViewState
import com.coronaintheworld.domain.usecase.CountriesUseCase
import com.coronaintheworld.mapper.CountryUIModelMapper

class HomeViewModel(
    private val countriesUseCase: CountriesUseCase,
    private val responseHandler: ResponseHandler
) : ViewModel() {

    val countries = liveData {
        emit(ViewState.loading())
        try {
            countriesUseCase()?.let {
                emit(
                    responseHandler.handleSuccess(
                        CountryUIModelMapper.map(it)
                    )
                )
            } ?: emit(responseHandler.handleException(Exception()))
        } catch (e: Exception) {
            emit(responseHandler.handleException(e))
        }
    }
}
