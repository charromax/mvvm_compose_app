package com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.charr0max.gamermvvmapp.data.core.Constants.USER_ARG
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.usecase.user.UserUseCases
import com.charr0max.gamermvvmapp.presentation.ui.screens.BaseOnboardingViewModel
import com.charr0max.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.charr0max.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userUseCases: UserUseCases,
    val resultingActivityHandler: ResultingActivityHandler,
    @ApplicationContext private val context: Context
) : BaseOnboardingViewModel() {

    var state by mutableStateOf(EditProfileState())
        private set

    var dialogState = mutableStateOf(false)

    var updateResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    var uploadImageResponse by mutableStateOf<Response<String>>(Response.Success(""))
    private var imageFile: File? = null
    val userString = savedStateHandle.get<String>(USER_ARG)
    val user = User.fromJson(userString!!)


    fun onUsernameInput(username: String) {
        state = state.copy(userName = username)
    }


    init {
        state = state.copy(userName = user.username, id = user.id, imageUrl = user.image)
    }

    fun updateUser(imageUrl: String = "") {
        viewModelScope.launch {
            updateResponse = Response.Loading
            val user = User(id = state.id, username = state.userName, image = imageUrl)
            val result = userUseCases.update(user)
            updateResponse = result
        }
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        state = state.copy(imageUrl = result?.toString().orEmpty())
        imageFile = result?.let { ComposeFileProvider.createFileFromUri(context, it) }
    }


    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        state = state.copy(imageUrl = result?.let {
            ComposeFileProvider.getPathFromBitmap(context, it)
        }.orEmpty())
        imageFile = File(state.imageUrl)
    }

    fun uploadImage() = viewModelScope.launch {
        uploadImageResponse = Response.Loading
        val result = imageFile?.let { userUseCases.uploadImage(it) }
        result?.let { uploadImageResponse = it }
    }

}