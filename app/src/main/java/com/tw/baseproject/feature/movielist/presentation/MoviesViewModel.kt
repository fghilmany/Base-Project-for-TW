package com.tw.baseproject.feature.movielist.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.baseproject.core.shared_resource.ResultData
import com.tw.baseproject.core.shared_resource.exception.Connectivity
import com.tw.baseproject.core.shared_resource.exception.DataEmpty
import com.tw.baseproject.core.shared_resource.exception.InvalidData
import com.tw.baseproject.feature.movielist.domain.LoadMovies
import com.tw.baseproject.feature.movielist.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieLoader: LoadMovies
): ViewModel() {

    private val viewModelState = MutableStateFlow(
        MoviesViewModelState(
            isLoading = true,
            failed = ""
        )
    )

    val moviesUiState = viewModelState
        .map(MoviesViewModelState::toMoviesUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value.toMoviesUiState()
        )

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            movieLoader.loadMovies().collect { result ->
                Log.d("loadMovies", "$result")
                viewModelState.update {
                    when (result) {
                        is ResultData.Success -> it.copy(
                            listMovies = result.data,
                            isLoading = false
                        )

                        is ResultData.Failure -> it.copy(
                            failed = when (result.throwable) {
                                is Connectivity -> "Connectivity"
                                is InvalidData -> "Invalid Data"
                                is DataEmpty -> "Data Empty"
                                else -> "Something Went Wrong"
                            },
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}

sealed interface MoviesUiState {
    val isLoading: Boolean
    val failed: String

    data class HasMovies(
        override val isLoading: Boolean,
        val listMovies: List<Movie>,
        override val failed: String
    ) : MoviesUiState

    data class NoMovies(
        override val isLoading: Boolean,
        override val failed: String,
    ) : MoviesUiState
}

data class MoviesViewModelState(
    val isLoading: Boolean = false,
    val listMovies: List<Movie> = emptyList(),
    val failed: String = ""
) {
    fun toMoviesUiState(): MoviesUiState =
        if (listMovies.isEmpty()) {
            MoviesUiState.NoMovies(
                isLoading = isLoading,
                failed = failed
            )

        } else {
            MoviesUiState.HasMovies(
                isLoading = isLoading,
                listMovies = listMovies,
                failed = failed
            )
        }
}
