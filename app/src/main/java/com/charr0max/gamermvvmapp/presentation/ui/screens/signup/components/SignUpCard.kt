package com.charr0max.gamermvvmapp.presentation.ui.screens.signup.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.charr0max.gamermvvmapp.domain.model.Response
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultButton
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultTextField
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.LoadingComponent
import com.charr0max.gamermvvmapp.presentation.ui.screens.signup.SignUpViewModel

@ExperimentalMaterial3Api
@Composable
fun SignUpCard(viewModel: SignUpViewModel = hiltViewModel()) {
    val state = viewModel.state
    Card(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 150.dp),
        shape = RoundedCornerShape(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "SIGN UP",
                modifier = Modifier.padding(top = 15.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "All fields are required",
                fontSize = 12.sp,
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                value = state.username,
                onValueChange = { viewModel.onUsernameInput(it) },
                label = "Username",
                icon = Icons.Default.Face,
                keyboardType = KeyboardType.Text,
                validateField = { viewModel.validateUserName(state.username) },
                errorMessage = viewModel.userNameError
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                value = state.email,
                onValueChange = { viewModel.onEmailInput(it) },
                label = "Email",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                validateField = { viewModel.validateEmail(state.email) },
                errorMessage = viewModel.emailError
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                value = state.password,
                onValueChange = { viewModel.onPasswordInput(it) },
                label = "Password",
                icon = Icons.Default.Lock,
                maskText = true,
                validateField = { viewModel.validatePwd(state.password) },
                errorMessage = viewModel.passwordError
            )
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordInput(it) },
                label = "Confirm Password",
                icon = Icons.Outlined.Lock,
                maskText = true,
                validateField = { viewModel.validateConfirmPassword(state.confirmPassword, state.password) },
                errorMessage = viewModel.confirmPasswordError
            )
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                icon = Icons.Default.ArrowForward, label = "SIGN UP",
                onclick = {
                    viewModel.signUp()
                },
                isEnabled = (viewModel.isValidUserName &&
                        viewModel.isValidEmail &&
                        viewModel.isValidPassword &&
                        viewModel.isValidConfirmPassword
                        )
            )
        }
    }
}