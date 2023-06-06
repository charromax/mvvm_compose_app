package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.usecase.auth.AuthUseCases
import com.charr0max.gamermvvmapp.domain.usecase.post.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsListViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {
    val currentUserId = authUseCases.currentUser()?.uid
    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
        private set
    var likeResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    var unlikeResponse by mutableStateOf<Response<Boolean>?>(null)
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

    fun like(postId: String) = viewModelScope.launch {
        likeResponse = Response.Loading
        likeResponse = postUseCases.like(postId, authUseCases.currentUser()?.uid.orEmpty())
    }
    fun unlike(postId: String) = viewModelScope.launch {
        unlikeResponse = Response.Loading
        unlikeResponse = postUseCases.unlike(postId, authUseCases.currentUser()?.uid.orEmpty())
    }
}
