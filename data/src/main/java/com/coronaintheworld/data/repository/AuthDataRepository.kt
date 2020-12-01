package com.coronaintheworld.data.repository

import com.coronaintheworld.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthDataRepository(private val firebaseAuth: FirebaseAuth) : AuthRepository {

    override fun isLogged() = firebaseAuth.currentUser != null
}
