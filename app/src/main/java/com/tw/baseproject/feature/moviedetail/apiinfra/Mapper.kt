package com.tw.baseproject.feature.moviedetail.apiinfra

import com.tw.moviedetail.api.RemoteDetailMovie
import com.tw.moviedetail.api.RemoteGenre

fun DetailMovieResponse.toAppLogic() = with(this){
    com.tw.moviedetail.api.RemoteDetailMovie(
        id,
        backdropPath ?: posterPath,
        title,
        releaseDate,
        genres.map { genres -> genres.toAppLogic() },
        overview
    )
}

fun GenresItem.toAppLogic() = with(this){
    com.tw.moviedetail.api.RemoteGenre(
        id,
        name
    )
}


