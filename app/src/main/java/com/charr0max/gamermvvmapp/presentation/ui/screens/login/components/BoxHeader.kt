package com.charr0max.gamermvvmapp.presentation.ui.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.charr0max.gamermvvmapp.R


@Composable
fun BoxHeader() {
    Box(
        modifier = Modifier
            .height(300.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier= Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(top = 20.dp),
                painter = painterResource(id = R.drawable.control),
                contentDescription = "Xbox 360 controller"
            )
            Text(text = "Firebase MVVM", modifier = Modifier.padding(vertical = 30.dp), color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}