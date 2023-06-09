package com.charr0max.gamermvvmapp.presentation.ui.screens.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.charr0max.gamermvvmapp.R

@Composable
fun BoxHeader(
    imageUrl: String? = null,
    onImageClick: (() -> Unit)? = null,
    defaultImage: Int? = null,
    defaultTitle: String? = null,
    shouldClip: Boolean = true,
) {
    Box(
        modifier = Modifier
            .height(170.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            imageUrl?.let {
                AsyncImage(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .clip(if(shouldClip) CircleShape else RoundedCornerShape(1.dp))
                        .clickable {
                            onImageClick?.invoke()
                        },
                    model = it,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } ?: run {
                Image(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(100.dp)
                        .width(100.dp)
                        .clip(if (shouldClip) CircleShape else RoundedCornerShape(1.dp))
                        .clickable { onImageClick?.invoke() },
                    painter = painterResource(id = defaultImage ?: R.drawable.user),
                    contentDescription = null,
                    contentScale = if (defaultImage != null) Fit else ContentScale.Crop
                )
            }
            if (defaultTitle != null) Text(
                text = defaultTitle,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}