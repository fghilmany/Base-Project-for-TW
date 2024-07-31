package com.tw.baseproject.feature.moviedetail.presentation

sealed interface DetailMovieUiState {
    val isLoading: Boolean
    val failed: String
    data class HasDetailMovie(
        override val isLoading: Boolean,
        var detailMovie: DetailMovie,
        override val failed: String
    ) : DetailMovieUiState

    data class NoDetailMovie(
        override val isLoading: Boolean,
        override val failed: String,
    ) : DetailMovieUiState
}