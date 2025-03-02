package com.prography.android.test.hyunjung.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.prography.android.test.hyunjung.R
import com.prography.android.test.hyunjung.data.model.Photo
import com.prography.android.test.hyunjung.ui.component.LoadingState
import com.prography.android.test.hyunjung.ui.theme.Black
import com.prography.android.test.hyunjung.ui.theme.Typography
import com.prography.android.test.hyunjung.ui.theme.White

@Composable
fun PhotoDetailScreen(
    onBackClick: () -> Unit = {},
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val photoDetail by viewModel.photoDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        when {
            isLoading -> {
                LoadingState()
            }

            errorMessage != null -> {
                // 에러 화면
            }

            photoDetail != null -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TopAppBar(
                        photo = photoDetail!!,
                        onBackClick = onBackClick,
                        onBookmarkClick = { viewModel.toggleBookmark() }
                    )
                    MainContent(photo = photoDetail!!)
                }
            }
        }
    }
}

@Composable
private fun TopAppBar(
    photo: Photo,
    onBackClick: () -> Unit = {},
    onBookmarkClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                painter = painterResource(R.drawable.ic_detail_close),
                contentDescription = "close",
                modifier = Modifier.padding(end = 16.dp),
                tint = Color.Unspecified
            )
        }

        Text(
            text = photo.user?.username ?: "Unknown user",
            color = White,
            modifier = Modifier.weight(1f),
            style = Typography.titleLarge
        )

        // 다운로드 버튼
        IconButton(onClick = { /* 다운로드 처리 */ }) {
            Icon(
                painter = painterResource(R.drawable.ic_detail_download),
                contentDescription = "download",
                tint = Color.White
            )
        }

        IconButton(onClick = { onBookmarkClick() }) {
            Icon(
                painter = painterResource(R.drawable.ic_detail_bookmark),
                contentDescription = "bookmark",
                tint = if (photo.isBookmarked) White else White.copy(0.3f),
            )
        }
    }
}

@Composable
private fun MainContent(photo: Photo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = photo.urls.regular,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 10.dp)
        ) {
            Text(
                text = photo.altDescription ?: "Untitled",
                color = White,
                style = Typography.titleLarge
            )

            Text(
                text = photo.description ?: "No description",
                color = White,
                maxLines = 2,
                modifier = Modifier.padding(top = 4.dp),
                style = Typography.labelSmall.copy(fontSize = 15.sp),
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "#${photo.tags.take(4).joinToString(" #") { it.title }}",
                color = White,
                modifier = Modifier.padding(top = 8.dp, bottom = 10.dp),
                style = Typography.labelSmall.copy(fontSize = 15.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPhotoDetailScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        PhotoDetailScreen()
    }
}