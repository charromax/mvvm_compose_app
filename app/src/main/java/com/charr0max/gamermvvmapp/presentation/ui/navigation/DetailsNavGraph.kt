package com.charr0max.gamermvvmapp.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.charr0max.gamermvvmapp.data.core.Constants
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post.NewPostScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.EditProfileScreen

fun NavGraphBuilder.detailsNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.EditProfile.route
    ) {
        composable(route = DetailsScreen.NewPost.route) {
            NewPostScreen(navHostController = navHostController)
        }
        composable(
            route = DetailsScreen.EditProfile.route,
            arguments = listOf(navArgument(Constants.USER_ARG) {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString(Constants.USER_ARG)?.let {
                EditProfileScreen(navHostController)
            }
        }
    }

}

sealed class DetailsScreen(val route: String) {
    object EditProfile : DetailsScreen("profile/edit/{user}") {
        fun passUsername(user: String) = "profile/edit/$user"
    }

    object NewPost : DetailsScreen("posts/new")
}