import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prography.android.test.hyunjung.R
import com.prography.android.test.hyunjung.ui.component.ImageItem
import com.prography.android.test.hyunjung.ui.component.ShimmerEffect
import com.prography.android.test.hyunjung.ui.home.HomeViewModel
import com.prography.android.test.hyunjung.ui.theme.White
import com.prography.android.test.hyunjung.ui.theme._10thandroidtestTheme
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val photos by viewModel.photos.collectAsState()
    val bookmarks by viewModel.bookmarks.collectAsState()
    val listState = rememberLazyStaggeredGridState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .filterNotNull()
            .filter { it >= photos.size - 1 } // 마지막 아이템이 보이면 다음 페이지 요청
            .collect {
                viewModel.fetchPhotos()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),

        ) {
        if (bookmarks.isNotEmpty()) {
            // item {
            Text(
                text = stringResource(R.string.bookmark),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        top = 30.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 10.dp
                    )
            )
            //}
            //item {
            LazyRow(modifier = Modifier.padding(horizontal = 10.dp)) {
                items(bookmarks) { photo ->
                    ImageItem(photo, onClick = { viewModel.toggleBookmark(photo) })
                }
            }
        }
        //}

        //item {
        Text(
            text = stringResource(R.string.latest_images),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    top = 22.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 10.dp
                )
        )
        //}

        //item {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .padding(horizontal = 10.dp),
            state = listState
        ) {
            items(
                photos.distinctBy { it.id },
                key = { photo -> photo.id }) { photo ->
                ImageItem(photo, onClick = { viewModel.toggleBookmark(photo) }, showOverlay = true)
            }

            item {
                ShimmerEffect()
            }
        }
        //}
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    _10thandroidtestTheme {
        HomeScreen()
    }
}