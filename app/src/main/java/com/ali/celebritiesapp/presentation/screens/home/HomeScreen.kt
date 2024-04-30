package com.ali.celebritiesapp.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ali.celebritiesapp.R
import com.ali.celebritiesapp.components.CelebritiesTopAppBar
import com.ali.celebritiesapp.data.remote.model.Artist
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    Scaffold(topBar = {
        CelebritiesTopAppBar(
            title = stringResource(id = R.string.app_name),
        )
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            val pagerState = rememberPagerState(
                pageCount = { 2 }
            )
            val coroutineScope = rememberCoroutineScope()
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = TabRowDefaults.primaryContainerColor,
                contentColor = TabRowDefaults.secondaryContentColor
            ) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    text = { Text(text = "Artists") },
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    }
                )
                Tab(
                    selected = pagerState.currentPage == 1,
                    text = { Text(text = "Venues") },
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    }
                )
            }

            HorizontalPager(
                state = pagerState,
//                userScrollEnabled = false
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (page == 0) {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ArtistList(viewModel)
                            }
                        }
                    } else {
                        Text(text = "Page $page")
                    }
                }
            }
        }
    }
}

@Composable
fun ArtistList(viewModel: HomeScreenViewModel) {
    val listOfArtists = viewModel.artists.toMutableList()
    if (viewModel.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items = listOfArtists) { artist ->
                ArtistRow(artist)
            }
        }
    }
}

@Composable
fun ArtistRow(artist: Artist) {
    Card(modifier = Modifier
        .clickable {

        }
        .fillMaxWidth()
        .height(110.dp)
        .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF673AB7)),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.padding(start = 5.dp)) {
                Text(
                    text = artist.name,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Text(
                    text = "${stringResource(id = R.string.artist_genre)} ${artist.genre}",
                    overflow = TextOverflow.Ellipsis,
                    fontStyle = FontStyle.Italic,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
            }
        }
    }
}
