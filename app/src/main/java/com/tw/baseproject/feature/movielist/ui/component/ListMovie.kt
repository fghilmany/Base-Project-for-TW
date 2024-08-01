package com.tw.baseproject.feature.movielist.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tw.baseproject.R
import com.tw.baseproject.feature.movielist.presentation.Movie
import com.tw.utilities.url.ImageUrl.Companion.TMDB_POSTER_IMAGE

@Composable
fun ListMovie(
    modifier: Modifier = Modifier,
    contentModifier: Modifier,
    items: List<Movie>,
    onItemClick: (Int) -> Unit
) {
    Column(
        modifier = contentModifier
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(items) { movie ->
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(movie.id)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(20.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = modifier.weight(2f)
                        ) {
                            Box(
                                modifier = modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFFCFCFF))
                                    .size(80.dp)
                            ) {
                                AsyncImage(
                                    model = TMDB_POSTER_IMAGE + movie.posterPath,
                                    contentDescription = "Icon",
                                    error = painterResource(id = R.drawable.baseline_broken_image_24),
                                    modifier = modifier
                                        .align(Alignment.Center)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = modifier.weight(5f)
                        ) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = movie.overview,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                                textAlign = TextAlign.Start,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis

                            )
                        }
                    }
                }
                HorizontalDivider(color = Color.Gray)
            }
        }
    }
}