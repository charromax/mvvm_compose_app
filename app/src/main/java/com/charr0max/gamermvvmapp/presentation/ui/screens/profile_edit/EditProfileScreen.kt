package com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.charr0max.gamermvvmapp.presentation.ui.navigation.AuthScreen
import com.charr0max.gamermvvmapp.presentation.ui.navigation.HomeBottomBarScreen
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.components.EditProfileContent
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.components.UploadImage

@Composable
fun EditProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {},
        bottomBar = {}
    ) {
        EditProfileContent(paddingValues = it)
    }
    UploadImage()
    EditProfile {
        navController.navigate(HomeBottomBarScreen.Profile.route)
    }
}