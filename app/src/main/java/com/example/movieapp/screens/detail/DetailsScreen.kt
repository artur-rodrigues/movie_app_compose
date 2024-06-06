package com.example.movieapp.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovieList
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.widgets.AppToolBar
import com.example.movieapp.widgets.MovieRow

@Preview(showBackground = true)
@Composable
private fun Exhibition() {
    MovieAppTheme {
        DetailsScreen(rememberNavController(), 1)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieData: Int?) {
    val movie = try {
        getMovieList().first { it.id == movieData }
    } catch (e: Exception) {
        null
    }

    Scaffold(topBar = {
        AppToolBar {
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                movie?.let { mov ->
                    Content(movie = mov)
                } ?: run {
                    Text(text = "Error", style = MaterialTheme.typography.headlineLarge)
                }

            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun Content(movie: Movie) {
    MovieRow(movie)

    HorizontalDivider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

    Text(text = "Movie Images")

    LazyRow {
        items(movie.images) {
            Card(modifier = Modifier
                .padding(12.dp)
                .size(240.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp)) {

                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Movie Poster"
                    )
                }
            }
        }
    }
}