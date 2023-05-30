package com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.components

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.charr0max.gamermvvmapp.presentation.ui.screens.dialog.DialogCapturePicture
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.EditProfileViewModel
import com.charr0max.gamermvvmapp.presentation.ui.screens.signup.components.BoxHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileContent(
    paddingValues: PaddingValues,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    viewModel.resultingActivityHandler.handle()
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) {
                viewModel.takePhoto()
            } else {
                Toast.makeText(context, "We need camera permission", Toast.LENGTH_SHORT).show()
            }
        }
    )
    val dialogState = viewModel.dialogState
    DialogCapturePicture(
        status = dialogState,
        takePhoto = {
            if (context.checkSelfPermission(CAMERA) == PERMISSION_GRANTED) {
                viewModel.takePhoto()
            } else {
                cameraPermissionLauncher.launch(CAMERA)
            }
        },
        pickImage = { viewModel.pickImage() }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
    ) {
        BoxHeader(viewModel = viewModel, onImageClick = {
            viewModel.dialogState.value = true
        })
        EditProfileCard(viewModel)
    }
}

