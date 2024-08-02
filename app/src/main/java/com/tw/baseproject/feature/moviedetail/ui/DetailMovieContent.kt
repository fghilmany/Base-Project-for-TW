package com.tw.baseproject.feature.moviedetail.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tw.moviedetail.presentation.DetailMovie
import com.tw.moviedetail.presentation.Genre
import com.tw.utilities.url.ImageUrl.Companion.TMDB_BACKDROP_IMAGE

@Composable
fun DetailMovieContent(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    detailMovie: com.tw.moviedetail.presentation.DetailMovie
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = contentModifier
            .padding(16.dp)
    ) {
        with(detailMovie){
            AsyncImage(
                model = TMDB_BACKDROP_IMAGE + backdropPath,
                contentDescription = "Icon",
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = releaseDate,
                style = TextStyle(
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                genres.onEach { genre ->
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .border(
                            border = BorderStroke(1.dp, color = Color.Black),
                            shape = RoundedCornerShape(8.dp)
                        )
                    ){
                        Text(
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                            text = genre.name,
                            style = TextStyle(
                                fontWeight = FontWeight.W400,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp
                            )
                        )
                    }

                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = overview,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Justify
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailMovieContentPreview() {
    DetailMovieContent(detailMovie = com.tw.moviedetail.presentation.DetailMovie(
        0,
        "",
        "Anabelle",
        "2020 - 12 - 12",
        listOf(
            com.tw.moviedetail.presentation.Genre(0, "Horror"),
            com.tw.moviedetail.presentation.Genre(1, "Comedy")
        ),
        "Test overview"
    )
    )
}