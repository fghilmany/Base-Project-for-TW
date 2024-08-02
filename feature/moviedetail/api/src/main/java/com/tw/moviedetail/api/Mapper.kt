package com.tw.moviedetail.api

import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.Genre

fun RemoteDetailMovie.toDomain() = with(this){
    DetailMovie(
        id,
        backdropPath,
        title,
        releaseDate,
        genres.map { genres -> genres.toDomain() },
        overview
    )
}

fun RemoteGenre.toDomain() = with(this){
    Genre(
        id,
        genre
    )
}