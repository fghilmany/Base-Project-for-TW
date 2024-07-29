package com.tw.baseproject.feature.moviedetail.cache

import com.tw.baseproject.feature.moviedetail.domain.DetailMovie
import com.tw.baseproject.feature.moviedetail.domain.Genre

fun DetailMovie.toAppLogic() = with(this){
    LocalDetailMovie(
        id = id,
        backdropPath = backdropPath,
        title = title,
        releaseDate = releaseDate,
        genres = genres.map { genres -> genres.toAppLogic(id) },
        overview = overview,
        isFavorite = isFavorite
    )
}

fun Genre.toAppLogic(movieId: Int) = with(this){
    LocalGenre(
        id,
        movieId,
        name
    )
}

fun LocalDetailMovie.toDomain() = with(this){
    DetailMovie(
        id = id,
        backdropPath = backdropPath,
        title = title,
        releaseDate = releaseDate,
        genres = genres.map { genres -> genres.toDomain() },
        overview = overview,
        isFavorite = isFavorite
    )
}


fun LocalGenre.toDomain() = with(this){
    Genre(
        id = id,
        name
    )
}