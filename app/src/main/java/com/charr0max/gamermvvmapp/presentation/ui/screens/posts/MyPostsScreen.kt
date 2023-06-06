package com.charr0max.gamermvvmapp.presentation.ui.screens.posts

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.presentation.ui.navigation.DetailsScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.my_posts.components.GetMyPosts

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
        GetMyPosts(navHostController = navHostController, paddingValues = it)
    }
}