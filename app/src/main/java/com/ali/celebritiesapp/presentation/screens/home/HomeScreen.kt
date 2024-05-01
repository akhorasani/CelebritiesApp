package com.ali.celebritiesapp.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import com.ali.celebritiesapp.R
import com.ali.celebritiesapp.components.ArtistList
import com.ali.celebritiesapp.components.CelebritiesTopAppBar
import com.ali.celebritiesapp.components.VenueList
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel, onItemClicked: (String, Int) -> Unit) {
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
                                ArtistList(viewModel, onItemClicked)
                            }
                        }
                    } else {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                VenueList(viewModel, onItemClicked)
                            }
                        }
                    }
                }
            }
        }
    }
}