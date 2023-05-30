package com.charr0max.gamermvvmapp.presentation.ui.screens.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.charr0max.gamermvvmapp.presentation.MainActivity
import com.charr0max.gamermvvmapp.presentation.ui.navigation.AuthScreen
import com.charr0max.gamermvvmapp.presentation.ui.navigation.DetailsScreen
import com.charr0max.gamermvvmapp.presentation.ui.navigation.Graph
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile.components.ProfileContent
import com.charr0max.gamermvvmapp.presentation.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity
    Scaffold {
        ProfileContent(
            it,
            onEditProfileClicked = { user ->
                navHostController.navigate(DetailsScreen.EditProfile.passUsername(user))
            },
            onLogoutClicked = {
                activity?.finish()
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePreview() {
    AppTheme() {
        ProfileScreen(rememberNavController())
    }
}