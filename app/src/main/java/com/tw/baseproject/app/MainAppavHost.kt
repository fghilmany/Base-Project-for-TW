package com.tw.baseproject.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tw.moviedetail.ui.navigation.detailMovieScreen
import com.tw.moviedetail.ui.navigation.navigateToMovieDetail
import com.tw.movielist.ui.navigation.moviesGraph
import com.tw.movielist.ui.navigation.moviesGraphRoute

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = com.tw.movielist.ui.navigation.moviesGraphRoute
) {
    NavHost(
        navController = navHostController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        moviesGraph(
            onMovieClick = navHostController::navigateToMovieDetail,
        ) {
            detailMovieScreen(
                popBackStack = navHostController::popBackStack,
                navHostController = navHostController
            )
        }
    }
}