package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultButton
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.TopBar
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post.components.NewPostContent

@Composable
fun NewPostScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = { TopBar(title = "New Post", navHostController = navHostController) },
        content = {
            NewPostContent(it)
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                icon = Icons.Default.ArrowForward,
                label = "Publish"
            ) {

            }
        }

    )

}