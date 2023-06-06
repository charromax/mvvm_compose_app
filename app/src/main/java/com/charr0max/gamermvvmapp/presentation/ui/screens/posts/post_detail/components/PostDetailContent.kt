package com.charr0max.gamermvvmapp.presentation.ui.screens.posts.post_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.charr0max.gamermvvmapp.domain.model.Post

@Composable
fun PostDetailContent(post: Post, paddingValues: PaddingValues) {
    Column(modifier = Modifier

        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = Crop,
            model = post.image,
            contentDescription = ""
        )
            if (post.user != null) {
                ElevatedCard(
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp, horizontal = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = post.user?.image,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .size(55.dp)
                                .clip(
                                    CircleShape
                                ),
                            contentScale = Crop
                        )
                        Column(
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text(
                                text = post.user?.username.orEmpty(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Text(
                                text = post.user?.email.orEmpty(),
                                color = Color.Gray,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(10.dp))
            }
        Text(
            text = post.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp)
        )
        ElevatedCard(
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(start = 13.dp, bottom = 15.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 7.dp)
            ) {
                Image(
                    painter = painterResource(id = post.postCategory?.icon!!),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = post.category,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        }
        Divider(
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            color = Color.DarkGray
        )
        Text(
            text = "Description",
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            modifier = Modifier.padding(start = 20.dp, bottom = 5.dp)
        )
        Text(
            text = post.description,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }
}