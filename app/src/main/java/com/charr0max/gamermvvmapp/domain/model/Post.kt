package com.charr0max.gamermvvmapp.domain.model

import com.charr0max.gamermvvmapp.R
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

enum class PostCategories(val icon: Int) {
    PC(R.drawable.icon_pc),
    PS4(R.drawable.icon_ps4),
    NINTENDO(R.drawable.icon_nintendo),
    XBOX(R.drawable.icon_xbox)
}


data class Post(
    var id: String = "",
    val name: String = "",
    val description: String = "",
    val category: String = "",
    var image: String = "",
    val userId: String = "",
    var user: User? = null,
    var likes: ArrayList<String> = arrayListOf()
) {
    val postCategory: PostCategories?
        get() = PostCategories.values().find { it.name.lowercase() == category.lowercase() }

    fun toJson(): String = Gson().toJson(
        Post(
            id, name, description, category,
            if (image.isNotEmpty()) URLEncoder.encode(
                image,
                StandardCharsets.UTF_8.toString()
            ) else "",
            userId,
            user?.let {
                User(
                    id = it.id,
                    username = it.username,
                    email = it.email,
                    image = if (it.image.isNotEmpty()) URLEncoder.encode(
                        it.image,
                        StandardCharsets.UTF_8.toString()
                    ) else "",
                )
            }
        )
    )

    companion object {
        fun fromJson(json: String): Post {
            return Gson().fromJson(json, Post::class.java)
        }
    }
}
