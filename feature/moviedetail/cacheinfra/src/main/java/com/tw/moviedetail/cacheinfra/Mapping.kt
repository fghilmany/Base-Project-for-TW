package com.tw.moviedetail.cacheinfra

import com.tw.moviedetail.cache.LocalDetailMovie
import com.tw.moviedetail.cache.LocalGenre
import com.tw.room_entity.DetailMovieEntity
import com.tw.room_entity.DetailMovieWithGenres
import com.tw.room_entity.LocalGenreEntity

fun LocalGenre.toDao() = with(this){
    LocalGenreEntity(
        genreId = id,
        movieId = movieId,
        name = name
    )
}

fun LocalDetailMovie.toDao() = with(this){
    DetailMovieEntity(
        id,
        backdropPath,
        title,
        releaseDate,
        overview,
        isFavorite
    )
}

fun DetailMovieWithGenres.toAppLogic() = with(this.detailMovieEntity){
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