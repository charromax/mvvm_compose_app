package com.charr0max.gamermvvmapp.presentation.ui.screens.posts

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.presentation.ui.navigation.DetailsScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.TopBar
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post.components.NewPostContent

@Composable
fun MyPostsScreen(navHostController: NavHostController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 80.dp),
                onClick = { navHostController.navigate(DetailsScreen.NewPost.route) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }) {
        Text(text = "new posts", Modifier.padding(it))
    }
}