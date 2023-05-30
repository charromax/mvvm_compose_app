package com.charr0max.gamermvvmapp.presentation.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.usecase.auth.AuthUseCases
import com.charr0max.gamermvvmapp.domain.usecase.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val userUseCases: UserUseCases
) : ViewModel() {

    var userData by mutableStateOf(User())
        private set
    private val currentUser = authUseCases.currentUser()

    init {
        getUserInfoById()
    }

    fun logout() {
        viewModelScope.launch { authUseCases.logout() }
    }

    private fun getUserInfoById() = viewModelScope.launch {
        currentUser?.uid?.let {
            userUseCases.getUserInfoById(it).collect { user ->
                userData = user
            }
        }
    }
}