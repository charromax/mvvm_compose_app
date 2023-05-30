package com.charr0max.gamermvvmapp.presentation.ui.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.charr0max.gamermvvmapp.R
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultButton
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultOutlinedButton
import com.charr0max.gamermvvmapp.presentation.ui.screens.profile.ProfileViewModel

@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel = hiltViewModel(),
    onEditProfileClicked: (user: String) -> Unit,
    onLogoutClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                alpha = .6f
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Welcome",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(120.dp))
                if (viewModel.userData.image.isNotEmpty()) {
                    AsyncImage(
                        modifier = Modifier
                            .size(115.dp)
                            .clip(
                                CircleShape
                            ),
                        model = viewModel.userData.image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = null,
                        modifier = Modifier.size(115.dp)
                    )
                }
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = viewModel.userData.username,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = viewModel.userData.email,
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(10.dp))
                DefaultOutlinedButton(
                    modifier = Modifier,
                    icon = Icons.Default.Edit,
                    label = "Edit profile",
                    onclick = {
                        onEditProfileClicked(viewModel.userData.toJson())
                    }
                )
                DefaultButton(
                    modifier = Modifier
                        .padding(top=10.dp),
                    icon = Icons.Default.ExitToApp,
                    label = "LOG OUT",
                    onclick = {
                        viewModel.logout()
                        onLogoutClicked()
                    }
                )
            }
        }
    }
}

