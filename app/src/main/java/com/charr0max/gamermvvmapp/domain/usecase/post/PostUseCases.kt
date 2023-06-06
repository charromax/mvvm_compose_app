package com.charr0max.gamermvvmapp.domain.usecase.post

data class PostUseCases(
    val create: CreatePost,
    val update: UpdatePost,
    val delete: DeletePost,
    val getPosts: GetPosts,
    val getPostsByUserID: GetPostsByUserID,
    val like: LikePost,
    val unlike: UnlikePost,
)
