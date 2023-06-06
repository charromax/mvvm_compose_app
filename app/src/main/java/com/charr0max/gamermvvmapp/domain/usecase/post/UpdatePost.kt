package com.charr0max.gamermvvmapp.domain.usecase.post

import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject

class UpdatePost @Inject constructor(
    private val repo: PostRepository
) {
    suspend operator fun invoke(post: Post, file: File?) = repo.update(post, file)
}