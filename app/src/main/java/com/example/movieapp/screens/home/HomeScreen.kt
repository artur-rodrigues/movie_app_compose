package com.example.movieapp.screens.home

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovieList
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.widgets.AppToolBar
import com.example.movieapp.widgets.MovieRow

@Preview
@Composable
private fun Exhibition() {
    MovieAppTheme {
        HomeScreen(rememberNavController())
    }
}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val activity = (LocalContext.current as? Activity)
    Scaffold(topBar = {
        AppToolBar {
            activity?.finish()
        }
    }) {
        MainContent(Modifier.padding(it), navController, getMovieList())
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, navController: NavController, movieList: List<Movie>) {
    Surface(modifier = modifier
        .padding(8.dp)
        .fillMaxSize(),
        color = MaterialTheme.colorScheme.background) {

        LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(items = movieList) {
                MovieRow(it) {movie ->
                    navController.navigate(
                        route = MovieScreens.DETAIL_SCREEN.screen + "/${movie.id}")
                }

                if(movieList.last().id == it.id) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}