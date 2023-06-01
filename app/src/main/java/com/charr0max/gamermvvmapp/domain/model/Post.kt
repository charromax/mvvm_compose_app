package com.charr0max.gamermvvmapp.domain.model

import com.charr0max.gamermvvmapp.R

enum class PostCategories(val icon: Int) {
    PC(R.drawable.icon_pc),
    PS4( R.drawable.icon_ps4),
    NINTENDO( R.drawable.icon_nintendo),
    XBOX( R.drawable.icon_xbox)
}


data class Post(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val category: String = "",
    var image: String = "",
    val userId: String = "",
    var user: User? = null
) {
    val postCategory: PostCategories? get() = PostCategories.values().find { it.name.lowercase() == category.lowercase() }
}
