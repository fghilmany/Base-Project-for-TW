package com.tw.baseproject.feature.movielist.presentation

sealed interface MoviesUiState {
    val isLoading: Boolean
    val failed: String

    data class HasMovies(
        override val isLoading: Boolean,
        val listMovies: List<Movie>,
        override val failed: String
    ) : MoviesUiState

    data class NoMovies(
        override val isLoading: Boolean,
        override val failed: String,
    ) : MoviesUiState
}