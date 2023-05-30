package com.charr0max.gamermvvmapp.data.repository

import android.net.Uri
import android.util.Log
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersRef: CollectionReference,
    private val imageStorageRef: StorageReference,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UsersRepository {
    override suspend fun create(user: User): Response<Boolean> {
        return withContext(dispatcher) {
            try {
                usersRef.document(user.id).set(user).await()
                Response.Success(true)
            } catch (e: java.lang.Exception) {
                Response.Failure(e)
            }
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return withContext(dispatcher) {
            try {
                val map = mutableMapOf<String, Any>()
                map["username"] = user.username
                map["image"] = user.image
                usersRef.document(user.id).update(map).await()
                Response.Success(true)
            } catch (e: java.lang.Exception) {
                Response.Failure(e)
            }
        }
    }

    override suspend fun uploadImage(file: File): Response<String> {
        return withContext(dispatcher) {
            try {
                val fromFile = Uri.fromFile(file)
                val ref = imageStorageRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val url = ref.downloadUrl.await()
                Response.Success(url.toString())
            } catch (e: java.lang.Exception) {
                Response.Failure(e)
            }
        }
    }

    override fun getUserById(uid: String): Flow<User> = callbackFlow {
        val snapshotListener = usersRef.document(uid).addSnapshotListener { snap, error ->
            val user = snap?.toObject(User::class.java) ?: User()
            if (error != null) {
                Log.e(this@UsersRepositoryImpl.javaClass.name, "getUserById: ", error)
            }
            trySend(user)
        }

        awaitClose { snapshotListener.remove() }
    }
}