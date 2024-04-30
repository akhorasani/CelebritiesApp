package com.ali.celebritiesapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.ali.celebritiesapp.R
import com.ali.celebritiesapp.data.remote.model.ArtistItem
import com.ali.celebritiesapp.data.remote.model.VenueItem
import com.ali.celebritiesapp.presentation.screens.home.HomeScreenViewModel

@Composable
fun CelebritiesAppLogo(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.displaySmall,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(start = 75.dp, top = 15.dp),
        color = Color(0xFF7D0C91)
    )
}

@ExperimentalMaterial3Api
@Composable
fun CelebritiesTopAppBar(
    title: String,
    icon: ImageVector? = null,
    onBackArrowClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(id = R.string.arrow_back),
                        tint = Color(0xFF7D0C91).copy(alpha = 0.7f),
                        modifier = Modifier.clickable { onBackArrowClicked.invoke() }
                    )
                    Spacer(modifier = Modifier.width(80.dp))
                }
                Text(
                    text = title,
                    color = Color(0xFF7D0C91),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Serif
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
        }, modifier = Modifier.background(Color.Transparent)
    )
}

@Composable
fun ArtistList(viewModel: HomeScreenViewModel, onItemClicked: (Int) -> Unit) {
    val listOfArtists = viewModel.artists.toMutableList()
    if (viewModel.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items = listOfArtists) { artist ->
                ArtistRow(artist, onItemClicked)
            }
        }
    }
}

@Composable
fun ArtistRow(artist: ArtistItem, onItemClicked: (Int) -> Unit) {
    Card(modifier = Modifier
        .clickable {
            onItemClicked(artist.id)
        }
        .fillMaxWidth()
        .height(110.dp)
        .padding(5.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFF673AB7)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF8000ff)),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(artist.imageUrl)
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = stringResource(id = R.string.artist_picture),
                modifier = Modifier
                    .height(150.dp)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = artist.name,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Text(
                    text = "${stringResource(id = R.string.artist_genre)}: ${artist.genre}",
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


@Composable
fun VenueList(viewModel: HomeScreenViewModel, onItemClicked: (Int) -> Unit) {
    val listOfVenues = viewModel.venues.toMutableList()
    if (viewModel.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items = listOfVenues) { venue ->
                VenueRow(venue, onItemClicked)
            }
        }
    }
}

@Composable
fun VenueRow(venue: VenueItem, onItemClicked: (Int) -> Unit) {
    Card(modifier = Modifier
        .clickable {
            onItemClicked(venue.id)
        }
        .fillMaxWidth()
        .height(110.dp)
        .padding(5.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFF673AB7)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF8000ff)),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(venue.imageUrl)
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build(),
                contentDescription = stringResource(id = R.string.artist_picture),
                modifier = Modifier
                    .height(150.dp)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = venue.name,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
            }
        }
    }
}

