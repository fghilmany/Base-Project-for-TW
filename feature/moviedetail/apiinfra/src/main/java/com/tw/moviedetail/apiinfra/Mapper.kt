package com.tw.moviedetail.apiinfra

import com.tw.moviedetail.api.RemoteDetailMovie
import com.tw.moviedetail.api.RemoteGenre

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


