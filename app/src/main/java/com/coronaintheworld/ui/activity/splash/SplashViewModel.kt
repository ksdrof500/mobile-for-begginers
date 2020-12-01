package com.coronaintheworld.ui.activity.splash

import androidx.lifecycle.ViewModel
import com.coronaintheworld.domain.usecase.AuthUseCase

class SplashViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    fun isCurrentUserAuth() = authUseCase()
}
