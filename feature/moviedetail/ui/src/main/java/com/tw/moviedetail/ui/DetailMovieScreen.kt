package com.tw.moviedetail.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tw.moviedetail.presentation.DetailMovieUiState
import com.tw.moviedetail.presentation.DetailMovieViewModel
import com.tw.utilities.widget.LoadingContent
import com.tw.utilities.widget.PullRefresh

@Composable
fun DetailMovieRoute(viewModel: DetailMovieViewModel, popBackStack: () -> Unit) {

    val detailMovieUiState by viewModel.moviesUiState.collectAsStateWithLifecycle()

    Log.d("loadMovies", "$detailMovieUiState")

    DetailMovieScreen(
        detailMovieUiState = detailMovieUiState,
        onRefreshMovies = viewModel::loadDetailMovie,
        popBackStack = popBackStack,

    )
}
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailMovieScreen(
    modifier: Modifier = Modifier,
    detailMovieUiState: DetailMovieUiState,
    onRefreshMovies: () -> Unit,
    popBackStack: () -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = detailMovieUiState.isLoading,
        onRefresh = onRefreshMovies
    )

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Movie Detail",
                    maxLines = 1,
                    color = Color.White
                )
            },
            /*colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Purple40
            ),*/
            navigationIcon = {
                IconButton(onClick = {
                    popBackStack()
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back from detail", tint = Color.White)
                }
            }
        )
    }, content = {
        val contentModifier = modifier
            .padding(it)
            .fillMaxSize()
            .pullRefresh(pullRefreshState)

        LoadingContent(
            pullRefreshState = pullRefreshState,
            loading = detailMovieUiState.isLoading,
            empty = when (detailMovieUiState) {
                is DetailMovieUiState.HasDetailMovie -> false
                is DetailMovieUiState.NoDetailMovie -> detailMovieUiState.isLoading
            },
            emptyContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    PullRefresh(
                        loading = detailMovieUiState.isLoading,
                        pullRefreshState = pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )
                }
            },
            content = {
                when (detailMovieUiState) {
                    is DetailMovieUiState.HasDetailMovie -> {
                        DetailMovieContent(
                            contentModifier = contentModifier,
                            detailMovie = detailMovieUiState.detailMovie,
                        )
                    }

                    is DetailMovieUiState.NoDetailMovie -> {
                        if (detailMovieUiState.failed.isEmpty()) {
                            Box(
                                modifier = modifier
                                    .fillMaxSize()
                                    .wrapContentSize(Alignment.Center)
                            ) {
                                Text(
                                    "List Movies Empty",
                                )
                            }
                        }
                    }
                }
            })

        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                detailMovieUiState.failed,
            )
        }
    })
}


