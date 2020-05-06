package com.coronaintheworld.remote.dto

import com.google.gson.annotations.SerializedName

data class DetailCountry(
    @SerializedName("Confirmed")
    val confirmed: Int,

    @SerializedName("Deaths")
    val deaths: Int,

    @SerializedName("Recovered")
    val recovered: Int,

    @SerializedName("Active")
    val active: Int
)