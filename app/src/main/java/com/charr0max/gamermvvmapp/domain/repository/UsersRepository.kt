package com.charr0max.gamermvvmapp.domain.repository

import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {
    suspend fun create(user: User): Response<Boolean>
    suspend fun update(user: User): Response<Boolean>
    suspend fun uploadImage(file: File): Response<String>
    fun getUserById(uid: String): Flow<User>
}