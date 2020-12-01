package com.coronaintheworld.data.mapper

import com.coronaintheworld.domain.entity.Country

object CountryMapper {

    fun map(countries: List<Country>) = countries.sortedBy { it.nameCountry }
}
