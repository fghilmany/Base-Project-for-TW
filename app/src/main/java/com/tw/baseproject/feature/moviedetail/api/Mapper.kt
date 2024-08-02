package com.tw.baseproject.feature.moviedetail.api

import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.Genre

fun RemoteDetailMovie.toDomain() = with(this){
    com.tw.moviedetail.domain.DetailMovie(
        id,
        backdropPath,
        title,
        releaseDate,
        genres.map { genres -> genres.toDomain() },
        overview
    )
}

fun RemoteGenre.toDomain() = with(this){
    com.tw.moviedetail.domain.Genre(
        id,
        genre
    )
}