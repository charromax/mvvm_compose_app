package com.charr0max.gamermvvmapp.presentation.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    validateField: () -> Unit = {},
    label: String,
    icon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    maskText: Boolean = false,
    singleLine: Boolean = true,
    errorMessage: String? = null,
) {
    Column {
        OutlinedTextField(
            value = value,
            modifier = modifier,
            label = { Text(text = label) },
            leadingIcon = {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                    )
                }
            },
            onValueChange = {
                onValueChange(it)
                validateField()
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (maskText) PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = singleLine
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = errorMessage.orEmpty(),
            fontSize = 11.sp,
            color = Color.Red
        )
    }

}