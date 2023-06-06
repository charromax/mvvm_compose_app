package com.charr0max.gamermvvmapp.domain.repository

import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {
    suspend fun create(post: Post, file: File): Response<Boolean>
    suspend fun update(post: Post, file: File?): Response<Boolean>
    suspend fun delete(postId: String): Response<Boolean>
    fun getPosts(): Flow<Response<List<Post>>>
    fun getPostsById(id: String): Flow<Response<List<Post>>>

    suspend fun like(postId: String, userId: String): Response<Boolean>
    suspend fun unlike(postId: String, userId: String): Response<Boolean>
}