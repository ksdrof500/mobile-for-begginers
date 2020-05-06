package com.coronaintheworld.common

import android.content.Context
import android.widget.Toast

fun Context.showErrorKT(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}