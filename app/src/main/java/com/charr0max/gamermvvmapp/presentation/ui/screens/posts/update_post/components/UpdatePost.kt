package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.LoadingComponent
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post.UpdatePostViewModel

@Composable
fun UpdatePost(viewModel: UpdatePostViewModel = hiltViewModel(), onPostUpdated: () -> Unit) {
    when (val res = viewModel.updatePostResponse) {
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
            if (res.data) {
                Toast.makeText(
                    LocalContext.current,
                    "Post created successfully!",
                    Toast.LENGTH_SHORT
                ).show()
                LaunchedEffect(Unit) {
                    onPostUpdated()
                }
            }
        }
    }
}