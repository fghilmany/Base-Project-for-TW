package com.tw.movielist.api

data class RemoteMovie(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val overview: String,
)
