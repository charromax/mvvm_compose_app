package com.charr0max.gamermvvmapp.domain.repository

import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {
    suspend fun create(post: Post, file: File): Response<Boolean>
    fun getPosts(): Flow<Response<List<Post>>>
}