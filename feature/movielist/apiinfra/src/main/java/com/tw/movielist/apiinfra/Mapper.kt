package com.tw.movielist.apiinfra

import com.tw.movielist.api.RemoteMovie

fun ResultsItem.toAppLogic() = with(this){
    RemoteMovie(
        id,
        posterPath,
        title,
        releaseDate,
        overview
    )
}
