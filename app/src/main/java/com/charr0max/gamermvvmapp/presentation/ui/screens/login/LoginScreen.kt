package com.charr0max.gamermvvmapp.presentation.ui.screens.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.charr0max.gamermvvmapp.presentation.ui.navigation.AuthScreen
import com.charr0max.gamermvvmapp.presentation.ui.navigation.Graph
import com.charr0max.gamermvvmapp.presentation.ui.screens.login.components.BottomContent
import com.charr0max.gamermvvmapp.presentation.ui.screens.login.components.Login
import com.charr0max.gamermvvmapp.presentation.ui.screens.login.components.LoginContent
import com.charr0max.gamermvvmapp.presentation.ui.theme.AppTheme

/**
 * a Scaffold container for all screens
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(name: String = "", navHostController: NavHostController) {
    Scaffold(topBar = {}, bottomBar = {
        BottomContent {
            navHostController.navigate(route = AuthScreen.SignUp.route)
        }
    }) {
        LoginContent(name = name, paddingValues = it)
    }
    Login(onLoginSuccess = {
        navHostController.navigate(route = Graph.HOME) {
            popUpTo(Graph.AUTHENTICATION) { inclusive = true }
        }
    })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    AppTheme() {
        LoginScreen("Android", rememberNavController())
    }
}