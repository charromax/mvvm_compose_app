package com.charr0max.gamermvvmapp.presentation.ui.screens.signup.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
@Composable
fun SignUpContent(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
    ) {
        BoxHeader()
        SignUpCard()
    }
}
