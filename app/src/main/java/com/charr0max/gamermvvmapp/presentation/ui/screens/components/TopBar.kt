package com.charr0max.gamermvvmapp.presentation.ui.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    isBackButtonVisible: Boolean = true,
    backButtonIcon: ImageVector = Icons.Default.ArrowBack,
    navHostController: NavHostController? = null,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
        title = { Text(text = title, fontSize = 19.sp) },
        navigationIcon = {
            if (isBackButtonVisible) {
                IconButton(onClick = {
                    navHostController?.popBackStack()
                }) {
                    Icon(
                        imageVector = backButtonIcon,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
        },
    )
}