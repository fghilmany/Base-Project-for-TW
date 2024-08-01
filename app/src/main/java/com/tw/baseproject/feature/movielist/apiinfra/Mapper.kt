package com.tw.baseproject.feature.movielist.apiinfra

import com.tw.movielist.api.RemoteMovie

fun ResultsItem.toAppLogic() = with(this){
    com.tw.movielist.api.RemoteMovie(
        id,
        posterPath,
        title,
        releaseDate,
        overview
    )
}
