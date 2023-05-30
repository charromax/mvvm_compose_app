package com.charr0max.gamermvvmapp.domain.usecase.auth

import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: User) = authRepository.registerUser(user)
}