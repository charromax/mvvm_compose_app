package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post.components

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.charr0max.gamermvvmapp.R
import com.charr0max.gamermvvmapp.domain.model.Post
import com.charr0max.gamermvvmapp.presentation.ui.screens.components.DefaultTextField
import com.charr0max.gamermvvmapp.presentation.ui.screens.dialog.DialogCapturePicture
import com.charr0max.gamermvvmapp.presentation.ui.screens.posts.update_post.UpdatePostViewModel
import com.charr0max.gamermvvmapp.presentation.ui.screens.signup.components.BoxHeader

@Composable
fun UpdatePostContent(
    paddingValues: PaddingValues,
    viewModel: UpdatePostViewModel = hiltViewModel()
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
            if (context.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                viewModel.takePhoto()
            } else {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        },
        pickImage = { viewModel.pickImage() }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val state = viewModel.state
            BoxHeader(
                defaultImage = R.drawable.add_image,
                imageUrl = viewModel.state.image,
                defaultTitle = "Select a picture",
                onImageClick = { viewModel.dialogState.value = true },
                shouldClip = false
            )
            DefaultTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, start = 20.dp, end = 20.dp),
                value = state.name,
                icon = Icons.Default.Face,
                onValueChange = {
                    viewModel.onNameInput(it)
                },
                label = "Videogame Name",
                errorMessage = "",
                validateField = {

                })
            DefaultTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
                value = state.description,
                singleLine = false,
                onValueChange = {
                    viewModel.onDescriptionInput(it)
                },
                label = "Description",
                errorMessage = "",
                validateField = {

                })
            Text(
                text = "Categories",
                modifier = Modifier.padding(vertical = 15.dp),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            viewModel.radioOptions.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .selectable(selected = (state.category == option.category),
                            onClick = { viewModel.onCategoryInput(option.category) }),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (state.category == option.category),
                        onClick = { viewModel.onCategoryInput(option.category) })
                    Text(text = option.category, modifier = Modifier.width(100.dp))
                    Image(
                        modifier = Modifier
                            .height(50.dp)
                            .padding(8.dp),
                        painter = painterResource(id = option.image),
                        contentDescription = ""
                    )
                }
            }
        }

    }
}