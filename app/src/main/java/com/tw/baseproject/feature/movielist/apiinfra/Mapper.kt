package com.tw.baseproject.feature.movielist.apiinfra

import com.tw.baseproject.feature.movielist.api.RemoteMovie

fun ResultsItem.toAppLogic() = with(this){
    RemoteMovie(
        id,
        posterPath,
        title,
        releaseDate,
        overview
    )
}
