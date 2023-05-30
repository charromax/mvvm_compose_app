package com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultButton
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultTextField
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile_edit.EditProfileViewModel

@ExperimentalMaterial3Api
@Composable
fun EditProfileCard(viewModel: EditProfileViewModel) {
    val state = viewModel.state
    Card(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 150.dp),
        shape = RoundedCornerShape(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "UPDATE PROFILE",
                modifier = Modifier.padding(top = 15.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Edit your username here",
                fontSize = 12.sp,
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = state.userName,
                onValueChange = { viewModel.onUsernameInput(it) },
                label = "Username",
                icon = Icons.Default.Face,
                keyboardType = KeyboardType.Text,
                validateField = { viewModel.validateUserName(state.userName) },
                errorMessage = viewModel.userNameError
            )
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                icon = Icons.Default.ArrowForward, label = "UPDATE PROFILE",
                onclick = {
                    viewModel.uploadImage()
                },
                isEnabled = (viewModel.isValidUserName)
            )
        }
    }
}