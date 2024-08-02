package com.tw.moviedetail.domain

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