package com.tw.baseproject.feature.moviedetail.api

import com.tw.baseproject.feature.moviedetail.domain.DetailMovie
import com.tw.baseproject.feature.moviedetail.domain.Genre

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