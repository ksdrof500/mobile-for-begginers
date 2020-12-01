package com.coronaintheworld.mapper

import android.content.Context
import com.coronaintheworld.R
import com.coronaintheworld.domain.entity.DetailCountry
import com.coronaintheworld.model.DetailCountryUiModel

object DetailCountryUIModelMapper {

    fun map(context: Context, detailCountry: DetailCountry) =
        detailCountry.run {
            DetailCountryUiModel(
                context.getString(R.string.confirmed, confirmed),
                context.getString(R.string.death, deaths),
                context.getString(R.string.recovered, recovered),
                context.getString(R.string.active, active)
            )
        }
}
