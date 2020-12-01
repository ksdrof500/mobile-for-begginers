package com.coronaintheworld.local

import android.content.Context
import android.content.SharedPreferences

class FavoritePreferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private val countryFavorite = "countryFavorite"

    fun saveCountry(slug: String) = preferences.edit().putString(countryFavorite, slug).apply()

    fun getCountry() = preferences.getString(countryFavorite, "")
}
