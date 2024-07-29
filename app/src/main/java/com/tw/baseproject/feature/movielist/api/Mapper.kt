package com.tw.baseproject.feature.movielist.api

import com.tw.baseproject.feature.movielist.domain.Movie

fun List<RemoteMovie>.toDomain() = this.map { item ->
    with(item){
        Movie(
            id,
            posterPath,
            title,
            releaseDate,
            overview
        )
    }
}