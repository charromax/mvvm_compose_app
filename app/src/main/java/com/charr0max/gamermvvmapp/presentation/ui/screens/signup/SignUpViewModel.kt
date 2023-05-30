package com.charr0max.gamermvvmapp.presentation.ui.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.model.User
import com.charr0max.gamermvvmapp.domain.usecase.auth.AuthUseCases
import com.charr0max.gamermvvmapp.domain.usecase.user.UserUseCases
import com.charr0max.gamermvvmapp.presentation.ui.screens.BaseOnboardingViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val userUseCases: UserUseCases
) : BaseOnboardingViewModel() {

    var state by mutableStateOf(SignUpState())
        private set
    var signUpResponse by mutableStateOf<Response<FirebaseUser>?>(null)
    var user = User()

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(pwd: String) {
        state = state.copy(password = pwd)
    }

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun onConfirmPasswordInput(pwd: String) {
        state = state.copy(confirmPassword = pwd)
    }

    fun signUp() {
        viewModelScope.launch {
            user.username = state.username
            user.email = state.email
            user.password = state.password
            signUpResponse = Response.Loading
            val result = authUseCases.signUp(user)
            signUpResponse = result
        }
    }

    fun createUser() {
        viewModelScope.launch {
            user.password = "" // should be hashed?
            user.id = authUseCases.currentUser()?.uid.orEmpty()
            userUseCases.create(user)
        }
    }

}