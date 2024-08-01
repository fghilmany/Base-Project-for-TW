package com.tw.movielist.presentation

data class MovieUiData(
    val isLoading: Boolean = false,
    val listMovies: List<Movie> = emptyList(),
    val failed: String = ""
) {
    fun toMoviesUiState(): MoviesUiState =
        if (listMovies.isEmpty()) {
            MoviesUiState.NoMovies(
                isLoading = isLoading,
                failed = failed
            )

        } else {
            MoviesUiState.HasMovies(
                isLoading = isLoading,
                listMovies = listMovies,
                failed = failed
            )
        }
}

data class Movie(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val overview: String,
)
