package com.example.movieapp.model


data class Movie(val id: Int,
                 val title: String,
                 val year: String,
                 val genre: String,
                 val director: String,
                 val actors: String,
                 val plot: String,
                 val poster: String,
                 val rating: String,
                 val images: List<String>
)

fun getMovieList(): List<Movie> {
    var count = 0
    val list = arrayListOf<Movie>()

    for(i in 1..10) {
        list.add(
            Movie(count++,
                "Title $count",
                "200$count",
                "Genre$count",
                "Director $count",
                "Actors $count",
                "Plot $count",
                "https://image.tmdb.org/t/p/w1280/vfEG79SQIg3p6B8rBLVeIo2BBhb.jpg",
                "$count",
                listOf(
                    "https://image.tmdb.org/t/p/w1280/vfEG79SQIg3p6B8rBLVeIo2BBhb.jpg",
                    "https://image.tmdb.org/t/p/w1280/vfEG79SQIg3p6B8rBLVeIo2BBhb.jpg",
                    "https://image.tmdb.org/t/p/w1280/vfEG79SQIg3p6B8rBLVeIo2BBhb.jpg",
                    "https://image.tmdb.org/t/p/w1280/vfEG79SQIg3p6B8rBLVeIo2BBhb.jpg",
                )
            )
        )
    }

    return list
}
