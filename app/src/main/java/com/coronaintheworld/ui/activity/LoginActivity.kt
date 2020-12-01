package com.coronaintheworld.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.coronaintheworld.R
import com.coronaintheworld.common.showErrorKT
import com.coronaintheworld.ui.activity.main.MainActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 666
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLoginProvider()
    }

    private fun setupLoginProvider() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.mipmap.ic_launcher)
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                nextToMainActivity()
            } else {
                showErrorKT(getString(R.string.error_login))
                Log.e("login", response?.error?.errorCode.toString())
            }
        }
    }

    private fun nextToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
