package com.charr0max.gamermvvmapp.domain.usecase.auth

data class AuthUseCases(
    val loginUseCase: LoginUseCase,
    val currentUser: GetCurrentUserUseCase,
    val logout: LogoutUseCase,
    val signUp: SignUpUseCase
)
