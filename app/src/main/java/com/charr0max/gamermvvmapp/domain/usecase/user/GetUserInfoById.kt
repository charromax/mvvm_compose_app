package com.charr0max.gamermvvmapp.domain.usecase.user

import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserInfoById @Inject constructor(
    private val repository: UsersRepository
) {
    operator fun invoke(uid: String) = repository.getUserById(uid)
}