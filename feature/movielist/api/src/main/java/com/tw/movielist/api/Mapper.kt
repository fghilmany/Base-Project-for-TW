package com.tw.movielist.api

import com.tw.movielist.domain.Movie

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