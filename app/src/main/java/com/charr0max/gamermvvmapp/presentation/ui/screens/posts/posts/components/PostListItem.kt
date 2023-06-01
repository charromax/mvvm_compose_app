package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.charr0max.gamermvvmapp.domain.model.Post

@Composable
fun PostListItem(post: Post) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Column {
            AsyncImage(
                model = post.image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = post.name,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = post.user?.username.orEmpty(),
                fontSize = 13.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = post.description,
                maxLines = 2,
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}