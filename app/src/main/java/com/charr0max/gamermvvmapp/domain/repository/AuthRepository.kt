package com.charr0max.gamermvvmapp.domain.repository

import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun logout()
    suspend fun registerUser(user:User): Response<FirebaseUser>
}