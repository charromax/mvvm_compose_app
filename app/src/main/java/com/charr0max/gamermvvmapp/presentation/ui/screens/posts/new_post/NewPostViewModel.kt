package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.charr0max.gamermvvmapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class CategoryRadioButton(
    val category: String, val image: Int
)

@HiltViewModel
class NewPostViewModel @Inject constructor() : ViewModel() {
    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
    )

    var state by mutableStateOf(NewPostState())

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
}