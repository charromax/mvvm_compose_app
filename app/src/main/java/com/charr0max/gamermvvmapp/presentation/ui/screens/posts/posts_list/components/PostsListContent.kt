package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.charr0max.gamermvvmapp.domain.model.Post

@Composable
fun PostsListContent(
    posts: List<Post>,
    withDelete: Boolean = false,
    onPostClicked: (Post) -> Unit,
    onDeletePost: ((String) -> Unit)? = null,
    onUpdatePost: ((Post) -> Unit)? = null,
    onLikeUnlikePost: ((String, Boolean) -> Unit)? = null,
    currentUserId: String? = null,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(bottom = 80.dp)
    ) {
        items(posts) {
            PostListItem(post = it, withDelete, onPostClicked, onDeletePost, onUpdatePost, onLikeUnlikePost, currentUserId)
        }
    }
}