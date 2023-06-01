package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts.components.GetPosts

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(
    navHostController: NavHostController,
) {
    Scaffold {
        GetPosts()
    }
}