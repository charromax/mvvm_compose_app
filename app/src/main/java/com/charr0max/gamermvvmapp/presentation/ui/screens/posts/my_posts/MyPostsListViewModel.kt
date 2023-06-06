package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.my_posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.usecase.auth.AuthUseCases
import com.charr0max.gamermvvmapp.domain.usecase.post.PostUseCases
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.LoadingComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostsListViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val authUseCases: AuthUseCases
) : ViewModel() {
    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
        private set

    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    init {
        getMyPosts()
    }

    private fun getMyPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postUseCases.getPostsByUserID(authUseCases.currentUser()?.uid.orEmpty()).collect {
            postsResponse = it
        }
    }

    fun deletePost(postId: String) = viewModelScope.launch {
        deleteResponse = Response.Loading
        deleteResponse = postUseCases.delete(postId)
    }
}
