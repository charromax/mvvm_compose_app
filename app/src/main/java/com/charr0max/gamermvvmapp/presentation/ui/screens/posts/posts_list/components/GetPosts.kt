package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.presentation.ui.navigation.DetailsScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.LoadingComponent
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list.PostsListViewModel


@Composable
fun GetPosts(
    viewModel: PostsListViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    when (val res = viewModel.postsResponse) {
        null -> return
        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                res.exception?.localizedMessage ?: "Unknown Error",
                Toast.LENGTH_SHORT
            ).show()
        }

        Response.Loading -> {
            LoadingComponent()
        }

        is Response.Success -> {
            PostsListContent(
                posts = res.data,
                onPostClicked = {
                    navHostController.navigate(DetailsScreen.PostDetail.passPost(it.toJson()))
                },
                onLikeUnlikePost = { postId: String, shouldLike: Boolean ->
                    if (shouldLike) viewModel.like(postId) else viewModel.unlike(postId)
                },
                currentUserId = viewModel.currentUserId
            )
        }
    }
}