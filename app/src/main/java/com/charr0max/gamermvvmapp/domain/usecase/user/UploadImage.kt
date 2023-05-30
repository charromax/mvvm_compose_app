package com.charr0max.gamermvvmapp.domain.usecase.user

import com.charr0max.gamermvvmapp.domain.repository.UsersRepository
import java.io.File
import javax.inject.Inject

class UploadImage @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(file: File) = repository.uploadImage(file)
}