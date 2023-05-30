package com.charr0max.gamermvvmapp.domain.usecase.user

data class UserUseCases(
    val create: CreateUser,
    val update: UpdateUser,
    val uploadImage: UploadImage,
    val getUserInfoById: GetUserInfoById,
) {
}