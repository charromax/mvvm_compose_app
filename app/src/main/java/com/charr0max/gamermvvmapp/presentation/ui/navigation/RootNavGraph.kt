package com.charr0max.gamermvvmapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.charr0max.gamermvvmapp.data.core.Constants.USER_ARG
import com.charr0max.gamermvvmapp.presentation.ui.screens.home.HomeScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile.ProfileScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.EditProfileScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION,
    ) {
        authNavGraph(navController)

        composable(
            route = Graph.HOME,
        ) {
            HomeScreen()
        }
    }
}
