package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovieList

@ExperimentalMaterial3Api
@Composable
fun AppToolBar(title: String = "Title", onBackClick: () -> Unit) {
    TopAppBar(colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        title = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart) {
                Text(text = title, modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)

                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp)
                        .clickable {
                            onBackClick()
                        })
            }
        })
}

@Preview
@Composable
fun MovieRow(movie: Movie = getMovieList()[0], onItemClick: (Movie) -> Unit = {}) {
    var expaded by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .shadow(elevation = 6.dp, shape = RoundedCornerShape(CornerSize(16.dp)))
        .clickable {
            onItemClick(movie)
        },
        shape = RoundedCornerShape(CornerSize(16.dp))
    ) {

        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {

                AsyncImage(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(100.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = "Movie Poster"
                )

                Column(modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()) {
                    Text(text = movie.title, style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.bodySmall)

                    AnimatedVisibility(visible = expaded) {
                        Column {
                            Text(modifier = Modifier.padding(top = 6.dp),
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                            fontSize = 13.sp)) {
                                        append("Plot: ")
                                    }

                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Light)) {
                                        append(movie.plot)
                                    }
                                })

                            HorizontalDivider(modifier = Modifier.padding(top = 6.dp, bottom = 6.dp))

                            Text(text = "Director: ${movie.director}",
                                style = MaterialTheme.typography.bodySmall
                            )

                            Text(text = "Actor: ${movie.actors}",
                                style = MaterialTheme.typography.bodySmall
                            )

                            Text(text = "Rating: ${movie.rating}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expaded = !expaded
                },
                contentAlignment = Alignment.Center) {

                Icon(imageVector = if(!expaded) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp),
                    tint = Color.DarkGray)
            }
        }
    }
}