package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charr0max.gamermvvmapp.R
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.usecase.auth.AuthUseCases
import com.charr0max.gamermvvmapp.domain.usecase.post.PostUseCases
import com.charr0max.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.charr0max.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

data class CategoryRadioButton(
    val category: String, val image: Int
)

@HiltViewModel
class NewPostViewModel @Inject constructor(
    val resultingActivityHandler: ResultingActivityHandler,
    @ApplicationContext private val context: Context,
    private val postUseCases: PostUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {
    var uploadImageResponse by mutableStateOf<Response<String>>(Response.Success(""))
    private var imageFile: File? = null
    var dialogState = mutableStateOf(false)

    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
    )

    var state by mutableStateOf(NewPostState())
        private set
    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onCategoryInput(category: String) {
        state = state.copy(category = category)
    }

    fun onImageInput(image: String) {
        state = state.copy(image = image)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        state = state.copy(image = result?.toString().orEmpty())
        imageFile = result?.let { ComposeFileProvider.createFileFromUri(context, it) }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        state = state.copy(image = result?.let {
            ComposeFileProvider.getPathFromBitmap(context, it)
        }.orEmpty())
        imageFile = File(state.image.orEmpty())
    }

    fun onNewPost() {
        val post = Post(
            name = state.name,
            description = state.description,
            category = state.category,
            userId = authUseCases.currentUser()?.uid.orEmpty(),
        )
        createPost(post)
    }

    private fun createPost(post: Post) = viewModelScope.launch {
        createPostResponse = Response.Loading
        createPostResponse = postUseCases.create(post, imageFile!!)
    }

    fun clearForm() {
        state = NewPostState()
        createPostResponse = null
    }
}