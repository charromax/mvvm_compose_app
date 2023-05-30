package com.charr0max.gamermvvmapp.domain.usecase.user

import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class CreateUser @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(user: User) = usersRepository.create(user)
}