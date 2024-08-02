package com.tw.moviedetail.presentation

data class DetailMovieUiData(
    var detailMovie: DetailMovie? = null,
    var isLoading: Boolean = false,
    var failed: String = ""
) {
    private val detailMoviesUiData = detailMovie
    fun toDetailMovieUiState(): DetailMovieUiState =
        if (detailMoviesUiData == null) {
            DetailMovieUiState.NoDetailMovie(
                isLoading = isLoading,
                failed = failed
            )

        } else {
            DetailMovieUiState.HasDetailMovie(
                isLoading = isLoading,
                detailMovie = detailMoviesUiData,
                failed = failed
            )
        }
}

data class DetailMovie(
    val id: Int,
    val backdropPath: String,
    val title: String,
    val releaseDate: String,
    val genres: List<Genre>,
    val overview: String,
    var isFavorite: Boolean = false
)

data class Genre(
    val id: Int,
    val name: String
)