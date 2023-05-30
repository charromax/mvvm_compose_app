package com.charr0max.gamermvvmapp.presentation.ui.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultButton
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultTextField
import com.charr0max.gamermvvmapp.presentation.ui.screens.login.LoginViewModel

@ExperimentalMaterial3Api
@Composable
fun LoginCard(
    name: String,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    Card(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 200.dp),
        shape = RoundedCornerShape(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "LOGIN",
                modifier = Modifier.padding(top = 15.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Hello $name, please login to continue...",
                fontSize = 12.sp,
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                value = state.email,
                onValueChange = { viewModel.onEmailInput(it) },
                validateField = { viewModel.validateEmail(state.email) },
                label = "Email",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMessage = viewModel.emailError
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                value = state.password,
                onValueChange = { viewModel.onPasswordInput(it) },
                validateField = { viewModel.validatePwd(state.password) },
                label = "Password",
                icon = Icons.Default.Lock,
                maskText = true,
                errorMessage = viewModel.passwordError
            )
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 45.dp),
                icon = Icons.Default.ArrowForward, label = "LOGIN",
                isEnabled = (viewModel.isValidEmail && viewModel.isValidPassword),
                onclick = {
                    viewModel.login()
                }
            )
        }
    }
}