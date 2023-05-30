package com.charr0max.gamermvvmapp.domain.usecase.user

import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class UpdateUser @Inject constructor(
    private val repo: UsersRepository
) {
    suspend operator fun invoke(user: User) = repo.update(user)
}