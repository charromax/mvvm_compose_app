package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.post_detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.TopBar
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post.components.CreateNewPost
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.post_detail.components.PostDetailContent

@Composable
fun PostDetailScreen(navHostController: NavHostController, viewModel: PostDetailViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }},
        content = {
            PostDetailContent(viewModel.post, it)
        },
        bottomBar = {

        }
    )
    CreateNewPost()
}