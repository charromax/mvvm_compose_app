package com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.LoadingComponent
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.EditProfileViewModel

@Composable
fun UploadImage(viewModel: EditProfileViewModel = hiltViewModel()) {
    when (val res = viewModel.uploadImageResponse) {
        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                res.exception?.localizedMessage ?: "Unknown Error",
                Toast.LENGTH_SHORT
            ).show()
        }

        Response.Loading -> LoadingComponent()
        is Response.Success -> {
            if (res.data.isNotEmpty()) viewModel.updateUser(res.data)
        }
    }

}