package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charr0max.gamermvvmapp.R
import com.charr0max.gamermvvmapp.data.core.Constants.POST_ARG
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.usecase.auth.AuthUseCases
import com.charr0max.gamermvvmapp.domain.usecase.post.PostUseCases
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post.CategoryRadioButton
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post.NewPostState
import com.charr0max.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.charr0max.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UpdatePostViewModel @Inject constructor(
    val resultingActivityHandler: ResultingActivityHandler,
    @ApplicationContext private val context: Context,
    private val postUseCases: PostUseCases,
    private val authUseCases: AuthUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uploadImageResponse by mutableStateOf<Response<String>>(Response.Success(""))
    private var imageFile: File? = null
    var dialogState = mutableStateOf(false)

    var state by mutableStateOf(NewPostState())
        private set
    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
    )

    val data = savedStateHandle.get<String>(POST_ARG)
    val post = Post.fromJson(data!!)

    init {
        setCurrentPost(post)
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onCategoryInput(category: String) {
        state = state.copy(category = category)
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

    private fun setCurrentPost(post: Post) {
        state = state.copy(
            image = post.image,
            name = post.name,
            description = post.description,
            category = post.category
        )
    }

    fun onUpdatePost() {
        val post = Post(
            id = post.id,
            name = state.name,
            image = state.image.orEmpty(),
            description = state.description,
            category = state.category,
            userId = authUseCases.currentUser()?.uid.orEmpty(),
        )
        updatePost(post)
    }

    fun updatePost(post: Post) = viewModelScope.launch {
        updatePostResponse = Response.Loading
        updatePostResponse = postUseCases.update(post, imageFile)
    }
}