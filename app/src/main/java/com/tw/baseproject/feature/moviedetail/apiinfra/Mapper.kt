package com.tw.baseproject.feature.moviedetail.apiinfra

import com.tw.baseproject.feature.moviedetail.api.RemoteDetailMovie
import com.tw.baseproject.feature.moviedetail.api.RemoteGenre

fun DetailMovieResponse.toAppLogic() = with(this){
    RemoteDetailMovie(
        id,
        backdropPath ?: posterPath,
        title,
        releaseDate,
        genres.map { genres -> genres.toAppLogic() },
        overview
    )
}

fun GenresItem.toAppLogic() = with(this){
    RemoteGenre(
        id,
        name
    )
}


