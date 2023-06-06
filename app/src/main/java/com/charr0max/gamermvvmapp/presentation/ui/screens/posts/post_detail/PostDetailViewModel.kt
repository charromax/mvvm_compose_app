package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.post_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.charr0max.gamermvvmapp.data.core.Constants.POST_ARG
import com.charr0max.gamermvvmapp.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val data = savedStateHandle.get<String>(POST_ARG)
    val post = Post.fromJson(data!!)
}