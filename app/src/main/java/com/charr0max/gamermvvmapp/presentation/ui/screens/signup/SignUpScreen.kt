package com.charr0max.gamermvvmapp.presentation.ui.screens.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.charr0max.gamermvvmapp.presentation.ui.navigation.Graph
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.TopBar
import com.charr0max.gamermvvmapp.presentation.ui.screens.signup.components.SignUp
import com.charr0max.gamermvvmapp.presentation.ui.screens.signup.components.SignUpContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopBar(title = "Sign up here", navHostController = navController) },
        content = {
            SignUpContent(paddingValues = it)
        },
        bottomBar = {}
    )
    SignUp {
        navController.popBackStack(Graph.AUTHENTICATION, inclusive = true)
        navController.navigate(Graph.HOME)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpPreview() {
    SignUpScreen(navController = rememberNavController())
}