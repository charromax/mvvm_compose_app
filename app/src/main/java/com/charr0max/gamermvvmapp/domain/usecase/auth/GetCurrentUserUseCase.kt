package com.charr0max.gamermvvmapp.domain.usecase.auth

import com.charr0max.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = authRepository.currentUser
}