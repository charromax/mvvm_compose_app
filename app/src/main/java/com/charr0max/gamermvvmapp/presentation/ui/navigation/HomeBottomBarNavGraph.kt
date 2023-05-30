package com.charr0max.gamermvvmapp.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.MyPostsScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.PostsScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile.ProfileScreen

@Composable
fun HomeBottomBarNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.Posts.route
    ) {
        composable(route = HomeBottomBarScreen.Profile.route) {
            ProfileScreen(navHostController = navHostController)
        }
        composable(route = HomeBottomBarScreen.Posts.route) {
            PostsScreen(navHostController = navHostController)
        }
        composable(route = HomeBottomBarScreen.MyPosts.route) {
            MyPostsScreen(navHostController = navHostController)
        }

        detailsNavGraph(navHostController)

    }
}

sealed class HomeBottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Posts: HomeBottomBarScreen(
        route = "post_list",
        title = "Posts",
        icon = Icons.Default.List
    )
    object MyPosts: HomeBottomBarScreen(
        route = "my_posts",
        title = "My Posts",
        icon = Icons.Outlined.List
    )
    object Profile: HomeBottomBarScreen(
        route = "profile",
        title = "My Profile",
        icon = Icons.Default.Person
    )
}