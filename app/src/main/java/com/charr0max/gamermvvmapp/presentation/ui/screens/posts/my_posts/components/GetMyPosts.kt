package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.my_posts.components

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.presentation.ui.navigation.DetailsScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.LoadingComponent
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.my_posts.MyPostsListViewModel
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list.components.PostsListContent


@Composable
fun GetMyPosts(
    viewModel: MyPostsListViewModel = hiltViewModel(),
    navHostController: NavHostController,
    paddingValues: PaddingValues
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
                withDelete = true,
                onPostClicked = {
                    navHostController.navigate(DetailsScreen.PostDetail.passPost(it.toJson()))
                },
                onDeletePost = {
                    viewModel.deletePost(it)
                },
                onUpdatePost = {
                    navHostController.navigate(DetailsScreen.UpdatePost.passPost(it.toJson()))
                }
            )
        }
    }
}