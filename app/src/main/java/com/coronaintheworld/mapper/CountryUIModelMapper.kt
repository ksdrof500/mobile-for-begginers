package com.coronaintheworld.mapper

import com.coronaintheworld.domain.entity.Country
import com.coronaintheworld.model.CountryUiModel

object CountryUIModelMapper {

    fun map(countries: List<Country>) =
        countries.map {
            it.run {
                CountryUiModel(
                    nameCountry,
                    slugCountry,
                    idFlag
                )
            }
        }
}
