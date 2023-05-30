package com.charr0max.gamermvvmapp.presentation.ui.screens.signup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.LoadingComponent
import com.charr0max.gamermvvmapp.presentation.ui.screens.signup.SignUpViewModel

@Composable
fun SignUp(viewModel: SignUpViewModel = hiltViewModel(), onSignUpSuccess: () -> Unit) {
    when (val res = viewModel.signUpResponse) {
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
            LaunchedEffect(Unit) {
                viewModel.createUser()
                onSignUpSuccess()
            }
        }
    }
}