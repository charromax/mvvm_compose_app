package com.charr0max.gamermvvmapp.domain.usecase.post

import com.charr0max.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPosts @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke() = repository.getPosts()
}