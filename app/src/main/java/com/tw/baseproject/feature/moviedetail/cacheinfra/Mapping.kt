package com.tw.baseproject.feature.moviedetail.cacheinfra

import com.tw.baseproject.feature.moviedetail.cache.LocalDetailMovie
import com.tw.baseproject.feature.moviedetail.cache.LocalGenre
import com.tw.baseproject.core.shared_models.room_entity.DetailMovieEntity
import com.tw.baseproject.core.shared_models.room_entity.DetailMovieWithGenres
import com.tw.baseproject.core.shared_models.room_entity.LocalGenreEntity

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