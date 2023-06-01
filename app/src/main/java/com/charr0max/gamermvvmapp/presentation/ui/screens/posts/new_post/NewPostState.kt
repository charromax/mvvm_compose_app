package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post

data class NewPostState(
    val image: String? = null,
    val name: String = "",
    val description: String = "",
    val category: String = ""
) {
    override fun toString(): String {
        return "NAME: $name\nIMAGE: $image\nDESC: $description\nCAT: $category"
    }
}
