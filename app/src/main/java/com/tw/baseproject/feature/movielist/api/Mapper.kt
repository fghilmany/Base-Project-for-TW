package com.tw.baseproject.feature.movielist.api

import com.tw.movielist.domain.Movie

fun List<RemoteMovie>.toDomain() = this.map { item ->
    with(item){
        com.tw.movielist.domain.Movie(
            id,
            posterPath,
            title,
            releaseDate,
            overview
        )
    }
}