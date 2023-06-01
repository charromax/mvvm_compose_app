package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.usecase.post.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postUseCases: PostUseCases
) : ViewModel() {
    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
        private set

    init {
        getPosts()
    }

    private fun getPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postUseCases.getPosts().collect {
            postsResponse = it
        }
    }
}
