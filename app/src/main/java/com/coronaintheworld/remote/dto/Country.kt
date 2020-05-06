package com.coronaintheworld.remote.dto

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("Country")
    val nameCountry: String,

    @SerializedName("Slug")
    val slugCountry: String,

    @SerializedName("ISO2")
    val idFlag: String
)
