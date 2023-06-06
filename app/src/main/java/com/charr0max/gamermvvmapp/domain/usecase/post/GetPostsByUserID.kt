package com.charr0max.gamermvvmapp.domain.usecase.post

import com.charr0max.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPostsByUserID @Inject constructor(
    private val repo: PostRepository
) {
    operator fun invoke(id: String) = repo.getPostsById(id)
}