package com.tw.baseproject.feature.moviedetail.cache

import com.tw.moviedetail.domain.DetailMovie
import com.tw.moviedetail.domain.Genre

fun com.tw.moviedetail.domain.DetailMovie.toAppLogic() = with(this){
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

fun com.tw.moviedetail.domain.Genre.toAppLogic(movieId: Int) = with(this){
    LocalGenre(
        id,
        movieId,
        name
    )
}

fun LocalDetailMovie.toDomain() = with(this){
    com.tw.moviedetail.domain.DetailMovie(
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
    com.tw.moviedetail.domain.Genre(
        id = id,
        name
    )
}