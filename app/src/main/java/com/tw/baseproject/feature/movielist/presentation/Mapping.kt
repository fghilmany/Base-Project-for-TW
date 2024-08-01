package com.tw.baseproject.feature.movielist.presentation

import com.tw.movielist.domain.Movie as DomainMovie

fun List<DomainMovie>.toUiData() = this.map {
    Movie(
        id = it.id,
        posterPath = it.posterPath,
        title = it.title,
        releaseDate = it.releaseDate,
        overview = it.overview
    )
}