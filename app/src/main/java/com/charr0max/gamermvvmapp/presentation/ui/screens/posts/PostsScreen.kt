package com.charr0max.gamermvvmapp.presentation.ui.screens.posts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun PostsScreen(
    navHostController: NavHostController
) {
    Scaffold {
        Text(text = "Posts screen", Modifier.padding(it))
    }
}