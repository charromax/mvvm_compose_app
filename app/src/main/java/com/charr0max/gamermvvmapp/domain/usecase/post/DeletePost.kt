package com.charr0max.gamermvvmapp.domain.usecase.post

import com.charr0max.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class DeletePost @Inject constructor(
    private val repository: PostRepository
) {
    suspend operator fun invoke(postId: String) = repository.delete(postId)
}