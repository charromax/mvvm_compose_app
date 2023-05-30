package com.charr0max.gamermvvmapp.presentation.ui.screens

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class BaseOnboardingViewModel() : ViewModel() {

    var isValidEmail: Boolean by mutableStateOf(false)
        protected set
    var emailError: String? by mutableStateOf(null)
        protected set
    var isValidPassword: Boolean by mutableStateOf(false)
        protected set
    var passwordError: String? by mutableStateOf(null)
        protected set
    var isValidUserName by mutableStateOf(false)
        protected set
    var userNameError: String? by mutableStateOf(null)
        protected set
    var isValidConfirmPassword: Boolean by mutableStateOf(false)
        protected set
    var confirmPasswordError: String? by mutableStateOf(null)
        protected set

    fun validateEmail(email: String) {
        // it's a valid email
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValidEmail = true
            emailError = null
        } else if (email.isNotEmpty()) {
            isValidEmail = false
            emailError = "Invalid Email!"
        } else {
            isValidPassword = false
            emailError = null
        }
    }

    fun validatePwd(pwd: String) {
        // it's valid password
        if (pwd.length >= 5) {
            isValidPassword = true
            passwordError = null
        } else if (pwd.isNotEmpty()) {
             isValidPassword = false
            passwordError = "Password too short!"
        } else {
            isValidPassword = false
            passwordError = null
        }
    }

    fun validateUserName(username: String) {
        if (username.isNotEmpty()) {
            isValidUserName = true
            userNameError = null
        }
    }

    fun validateConfirmPassword(confPwd: String, currentPwd: String) {
        if (confPwd == currentPwd) {
            isValidConfirmPassword = true
            confirmPasswordError = null
        } else {
            isValidConfirmPassword = false
            confirmPasswordError = "Passwords don't match!"
        }
    }

}