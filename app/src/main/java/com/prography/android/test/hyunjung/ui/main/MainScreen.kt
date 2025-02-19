import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prography.android.test.hyunjung.R
import com.prography.android.test.hyunjung.ui.component.ImageItem
import com.prography.android.test.hyunjung.ui.main.MainViewModel
import com.prography.android.test.hyunjung.ui.theme.Gray30
import com.prography.android.test.hyunjung.ui.theme._10thandroidtestTheme

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val photos by viewModel.photos.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            painter = painterResource(R.drawable.img_prography_logo),
            contentDescription = "Prography"
        )

        HorizontalDivider(thickness = 1.dp, color = Gray30)

        LazyColumn(modifier = Modifier.padding(horizontal = 10.dp)) {

            item {
                if (bookmarks.isNotEmpty()) {
                    Text(
                        text = "북마크",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(
                            top = 30.dp,
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 10.dp
                        )
                    )

                    LazyRow(modifier = Modifier.padding(horizontal = 10.dp)) {
                        items(bookmarks) { photo ->
                            ImageItem(photo, onClick = { viewModel.toggleBookmark(photo) })
                        }
                    }
                }
            }

            item {
                Text(
                    text = "최신 이미지",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = 22.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 10.dp
                    )
                )
            }

            items(photos.distinctBy { it.id }, key = { photo -> photo.id }) { photo ->
                ImageItem(photo, onClick = { viewModel.toggleBookmark(photo) })
            }

            item {
                LaunchedEffect(Unit) { viewModel.fetchPhotos() }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    _10thandroidtestTheme {
        MainScreen()
    }
}