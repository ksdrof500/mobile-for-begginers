package com.coronaintheworld.domain.usecase

import com.coronaintheworld.domain.repository.AuthRepository

class AuthUseCase(private val authRepository: AuthRepository) {

    operator fun invoke() = authRepository.isLogged()
}
