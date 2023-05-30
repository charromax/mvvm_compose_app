package com.charr0max.gamermvvmapp.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.charr0max.gamermvvmapp.presentation.ui.screens.login.LoginScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navHostController = navHostController)
        }

        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(navHostController)
        }
    }

}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object SignUp : AuthScreen("signup")
}
