package com.prography.android.test.hyunjung.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.prography.android.test.hyunjung.R
import com.prography.android.test.hyunjung.navigation.AppNavHost
import com.prography.android.test.hyunjung.navigation.TopLevelDestination
import com.prography.android.test.hyunjung.ui.theme.Gray30
import com.prography.android.test.hyunjung.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(White),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.img_prography_logo),
                                contentDescription = "Prography",
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                )
                HorizontalDivider(thickness = 1.dp, color = Gray30)
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                AppNavHost()
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                TopLevelDestination.entries.forEach { destination ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = destination.iconId),
                                contentDescription = null
                            )
                        },
                        selected = destination == TopLevelDestination.HOME,
                        onClick = {
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            unselectedIconColor = Color.White.copy(alpha = 0.3f),
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    )
}