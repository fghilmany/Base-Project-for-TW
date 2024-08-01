package com.tw.baseproject.feature.moviedetail.cacheinfra

import com.tw.baseproject.feature.moviedetail.cache.LocalDetailMovie
import com.tw.baseproject.feature.moviedetail.cache.LocalGenre
import com.tw.room_entity.DetailMovieEntity
import com.tw.room_entity.DetailMovieWithGenres
import com.tw.room_entity.LocalGenreEntity

fun LocalGenre.toDao() = with(this){
    com.tw.room_entity.LocalGenreEntity(
        genreId = id,
        movieId = movieId,
        name = name
    )
}

fun LocalDetailMovie.toDao() = with(this){
    com.tw.room_entity.DetailMovieEntity(
        id,
        backdropPath,
        title,
        releaseDate,
        overview,
        isFavorite
    )
}

fun com.tw.room_entity.DetailMovieWithGenres.toAppLogic() = with(this.detailMovieEntity){
    LocalDetailMovie(
        id,
        backdropPath,
        title,
        releaseDate,
        overview,
        isFavorite,
        this@toAppLogic.genres.map { genre -> with(genre){
            LocalGenre(
                id, movieId, name
            )
        } }
    )
}