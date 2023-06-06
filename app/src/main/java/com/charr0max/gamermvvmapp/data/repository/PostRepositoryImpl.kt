package com.charr0max.gamermvvmapp.data.repository

import android.net.Uri
import com.charr0max.gamermvvmapp.data.core.Constants
import com.charr0max.gamermvvmapp.data.core.Constants.USER_ID
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.repository.PostRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostRepositoryImpl @Inject constructor(
    @Named(Constants.POSTS) private val postRef: CollectionReference,
    @Named(Constants.USERS) private val usersRef: CollectionReference,
    @Named(Constants.POSTS) private val storagePostRef: StorageReference,
    private val dispatcher: CoroutineDispatcher
) : PostRepository {
    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return withContext(dispatcher) {
            try {
                // SAVE IMAGE
                val fromFile = Uri.fromFile(file)
                val ref = storagePostRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val url = ref.downloadUrl.await()
                // SAVE POST
                post.image = url.toString()
                postRef.add(post).await()
                Response.Success(true)
            } catch (e: Exception) {
                e.printStackTrace()
                Response.Failure(e)
            }
        }
    }

    override suspend fun update(post: Post, file: File?): Response<Boolean> {
        return withContext(dispatcher) {
            try {
                if (file != null) {
                    // SAVE IMAGE
                    val fromFile = Uri.fromFile(file)
                    val ref = storagePostRef.child(file.name)
                    val uploadTask = ref.putFile(fromFile).await()
                    val url = ref.downloadUrl.await()
                    post.image = url.toString()
                }

                // SAVE POST
                val postMap = hashMapOf<String, Any>(
                    "name" to post.name,
                    "description" to post.description,
                    "image" to post.image,
                    "category" to post.category,
                )
                postRef.document(post.id).update(postMap).await()
                Response.Success(true)
            } catch (e: Exception) {
                e.printStackTrace()
                Response.Failure(e)
            }
        }
    }

    override suspend fun delete(postId: String): Response<Boolean> {
        return withContext(dispatcher) {
            try {
                postRef.document(postId).delete().await()
                Response.Success(true)
            } catch (e: Exception) {
                e.printStackTrace()
                Response.Failure(e)
            }
        }
    }

    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {
        val snapShotListener = postRef.addSnapshotListener { snap, error ->
            CoroutineScope(dispatcher).launch {
                val postResponse = if (snap != null) {
                    val posts = snap.toObjects(Post::class.java)
                    val userIdList = mutableListOf<String>()
                    posts.forEach {
                        userIdList.add(it.userId)
                    }
                    snap.documents.forEachIndexed { index, documentSnapshot ->
                        posts[index].id = documentSnapshot.id
                    }
                    val filteredUserIdList = userIdList.toSet().toList()
                    filteredUserIdList.map { id ->
                        async {
                            val user = usersRef.document(id).get().await()
                                .toObject(User::class.java)
                            posts.forEach {
                                if (it.userId == id) {
                                    it.user = user
                                }
                            }
                        }
                    }.forEach { it.await() }
                    Response.Success(posts)
                } else {
                    Response.Failure(error)
                }
                trySend(postResponse)
            }
        }
        awaitClose { snapShotListener.remove() }
    }

    override fun getPostsById(id: String): Flow<Response<List<Post>>> = callbackFlow {
        if (id.isNotEmpty()) {
            val snapShotListener =
                postRef.whereEqualTo(USER_ID, id).addSnapshotListener { snap, error ->
                    CoroutineScope(dispatcher).launch {
                        val postResponse = if (snap != null) {
                            val posts = snap.toObjects(Post::class.java)
                            snap.documents.forEachIndexed { index, documentSnapshot ->
                                posts[index].id = documentSnapshot.id
                            }
                            Response.Success(posts)
                        } else {
                            Response.Failure(error)
                        }
                        trySend(postResponse)
                    }
                }
            awaitClose { snapShotListener.remove() }
        } else close()
    }

    override suspend fun like(postId: String, userId: String): Response<Boolean> {
        return withContext(dispatcher) {
            try {
                postRef.document(postId).update("likes", FieldValue.arrayUnion(userId)).await()
                Response.Success(true)
            } catch (e: Exception) {
                e.printStackTrace()
                Response.Failure(e)
            }
        }
    }

    override suspend fun unlike(postId: String, userId: String): Response<Boolean> {
        return withContext(dispatcher) {
            try {
                postRef.document(postId).update("likes", FieldValue.arrayRemove(userId)).await()
                Response.Success(true)
            } catch (e: Exception) {
                e.printStackTrace()
                Response.Failure(e)
            }
        }
    }
}