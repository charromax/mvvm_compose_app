package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultButton
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post.components.CreateNewPost
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.post_detail.components.PostDetailContent
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post.components.UpdatePost
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post.components.UpdatePostContent

@Composable
fun UpdatePostScreen(navHostController: NavHostController, viewModel: UpdatePostViewModel = hiltViewModel()) {
    Scaffold(
        topBar = { IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }},
        content = {
            UpdatePostContent(it)
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                icon = Icons.Default.ArrowForward,
                label = "Update"
            ) {
                viewModel.onUpdatePost()
            }
        }
    )
    UpdatePost {
        navHostController.popBackStack()
    }
}