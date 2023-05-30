package com.charr0max.gamermvvmapp.data.repository

import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    AuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Response<FirebaseUser> {
        return withContext(dispatcher) {
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                Response.Success(result.user!!)
            } catch (e: java.lang.Exception) {
                Response.Failure(e)
            }
        }
    }

    override suspend fun logout() {
        withContext(dispatcher) { firebaseAuth.signOut() }
    }

    override suspend fun registerUser(user: User): Response<FirebaseUser> {
        return withContext(dispatcher) {
            try {
                val result =
                    firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
                Response.Success(result.user!!)
            } catch (e: java.lang.Exception) {
                Response.Failure(e)
            }
        }
    }
}