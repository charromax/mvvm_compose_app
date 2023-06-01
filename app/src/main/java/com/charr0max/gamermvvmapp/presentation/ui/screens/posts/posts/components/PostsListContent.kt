package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.charr0max.gamermvvmapp.domain.model.Post

@Composable
fun PostsListContent(posts: List<Post>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(posts) {
            PostListItem(post = it)
        }
    }
}