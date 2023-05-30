package com.charr0max.gamermvvmapp.presentation.ui.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.domain.usecase.auth.AuthUseCases
import com.charr0max.gamermvvmapp.presentation.ui.screens.BaseOnboardingViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : BaseOnboardingViewModel() {

    var state by mutableStateOf(LoginState())

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    private val currentUser = authUseCases.currentUser()

    init {
        if (currentUser != null) {
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(pwd: String) {
        state = state.copy(password = pwd)
    }

    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.loginUseCase(state.email, state.password)
        loginResponse = result
    }

}