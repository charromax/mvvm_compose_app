package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.my_posts

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.my_posts.components.GetMyPosts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(
    navHostController: NavHostController,
) {
    Scaffold {
        GetMyPosts(navHostController = navHostController, paddingValues = it)
    }
}