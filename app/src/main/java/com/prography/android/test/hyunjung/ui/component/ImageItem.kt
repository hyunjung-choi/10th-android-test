package com.prography.android.test.hyunjung.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.prography.android.test.hyunjung.data.model.Photo
import com.prography.android.test.hyunjung.ui.theme.Black
import com.prography.android.test.hyunjung.ui.theme.Typography
import com.prography.android.test.hyunjung.ui.theme.White

@Composable
fun ImageItem(
    photo: Photo,
    onClick: () -> Unit,
    showOverlay: Boolean = false
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
    ) {
        Box {
            if (showOverlay) {
                AsyncImage(
                    model = photo.urls.regular,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Transparent, Black.copy(alpha = 0.3f)),
                                startY = 100f
                            )
                        )
                ) {
                    Text(
                        text = "titletitletitle\n" +
                                "타이틀은최대2줄까지",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(top = 12.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
                        style = Typography.labelSmall.copy(
                            shadow = Shadow(
                                color = Black.copy(alpha = 0.25f),
                                offset = Offset(0f, 2f),
                                blurRadius = 4f
                            )
                        ),
                        maxLines = 2,
                        color = White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            } else {
                AsyncImage(
                    model = photo.urls.regular,
                    contentDescription = null,
                    modifier = Modifier
                        .height(128.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}