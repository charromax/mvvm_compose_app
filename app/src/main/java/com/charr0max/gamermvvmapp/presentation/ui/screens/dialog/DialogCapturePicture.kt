package com.charr0max.gamermvvmapp.presentation.ui.screens.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogCapturePicture(
    status: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickImage: () -> Unit,
) {
    if (status.value) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            onDismissRequest = { status.value = false },
            title = {
                Text(text = "Choose an option", fontSize = 20.sp)
            },
            confirmButton = {
                Button(onClick = {
                    status.value = false
                    pickImage()
                }) {
                    Text(text = "Gallery")
                }
            },

            dismissButton = {
                Button(onClick = {
                    status.value = false
                    takePhoto()
                }) {
                    Text(text = "Camera")
                }
            }

        )
    }
}