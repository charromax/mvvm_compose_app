package com.charr0max.gamermvvmapp.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.charr0max.gamermvvmapp.data.core.Constants
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.new_post.NewPostScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.post_detail.PostDetailScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post.UpdatePostScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post.components.UpdatePostContent
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

        composable(
            route = DetailsScreen.PostDetail.route,
            arguments = listOf(navArgument(Constants.POST_ARG) {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString(Constants.POST_ARG)?.let {
                PostDetailScreen(navHostController)
            }
        }

        composable(
            route = DetailsScreen.UpdatePost.route,
            arguments = listOf(navArgument(Constants.POST_ARG) {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString(Constants.POST_ARG)?.let {
                UpdatePostScreen(navHostController)
            }
        }
    }

}

sealed class DetailsScreen(val route: String) {
    object EditProfile : DetailsScreen("profile/edit/{user}") {
        fun passUsername(user: String) = "profile/edit/$user"
    }

    object NewPost : DetailsScreen("posts/new")
    object PostDetail : DetailsScreen("posts/detail/{post}"){
        fun passPost(post: String) = "posts/detail/$post"
    }
    object UpdatePost : DetailsScreen("posts/update/{post}"){
        fun passPost(post: String) = "posts/update/$post"
    }
}