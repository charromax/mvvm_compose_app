package com.charr0max.gamermvvmapp.domain.usecase.post

import com.charr0max.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class LikePost  @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: String, userId: String) = repository.like(postId, userId)
}