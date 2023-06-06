package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list.components.GetPosts
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list.components.LikePost
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list.components.UnlikePost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(
    navHostController: NavHostController,
) {
    Scaffold {
        GetPosts(navHostController = navHostController)
    }
    LikePost()
    UnlikePost()
}