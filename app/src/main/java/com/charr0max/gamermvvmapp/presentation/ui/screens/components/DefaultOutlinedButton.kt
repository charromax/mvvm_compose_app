package com.charr0max.gamermvvmapp.presentation.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DefaultOutlinedButton(
    modifier: Modifier,
    icon: ImageVector,
    label: String,
    color: Color = MaterialTheme.colorScheme.primary,
    isEnabled: Boolean = true,
    onclick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier.apply { background(color = color) },
        shape = RoundedCornerShape(6.dp),
        onClick = onclick,
        enabled = isEnabled
    ) {
        Icon(imageVector = icon, contentDescription = "")
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = label)
    }
}