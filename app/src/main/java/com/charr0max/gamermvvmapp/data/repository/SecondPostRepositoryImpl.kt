package com.charr0max.gamermvvmapp.data.repository

import android.net.Uri
import com.charr0max.gamermvvmapp.data.core.Constants
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.repository.PostRepository
import com.google.firebase.firestore.CollectionReference
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

class SecondPostRepositoryImpl @Inject constructor(
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

    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {
        val snapShotListener = postRef.addSnapshotListener { snap, error ->
            CoroutineScope(dispatcher).launch {
                val postResponse = if (snap != null) {
                    val posts = snap.toObjects(Post::class.java)
                    posts.map {
                        async {
                            it.user = usersRef.document(it.userId).get().await()
                                .toObject(User::class.java)
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
}