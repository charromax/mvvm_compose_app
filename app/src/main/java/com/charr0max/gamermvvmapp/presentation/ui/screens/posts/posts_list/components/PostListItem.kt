package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.posts_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.charr0max.gamermvvmapp.domain.model.Post

@Composable
fun PostListItem(
    post: Post,
    isMyPost: Boolean = false,
    onCardClicked: (Post) -> Unit,
    onDeletePost: ((String) -> Unit)? = null,
    onEditPost: ((Post) -> Unit)? = null,
    onLikeUnlikePost: ((String, Boolean) -> Unit)? = null,
    currentUserId: String?,
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(10.dp)
            .clickable { onCardClicked(post) }
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
            if (isMyPost) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { onDeletePost?.invoke(post.id) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "")
                    }
                    IconButton(onClick = { onEditPost?.invoke(post) }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                    }
                }

            } else {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    if (post.likes.contains(currentUserId)) {
                        IconButton(onClick = { onLikeUnlikePost?.invoke(post.id, false) }) {
                            Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "")
                        }
                    } else {
                        IconButton(onClick = { onLikeUnlikePost?.invoke(post.id, true) }) {
                            Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "")
                        }
                    }
                    Text(text = post.likes.size.toString())
                }

            }
        }
    }
}