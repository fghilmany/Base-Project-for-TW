package com.tw.baseproject.feature.moviedetail.presentation

import com.tw.baseproject.feature.moviedetail.domain.DetailMovie as DomainDetailMovie

fun DomainDetailMovie.toUiData() = DetailMovie(
    id = id,
    backdropPath = backdropPath,
    title = title,
    releaseDate = releaseDate,
    overview = overview,
    genres = genres.map {
        Genre(
            id = it.id,
            name = it.name,
        )
    },
)