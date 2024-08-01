package com.tw.baseproject.feature.movielist.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.shared_resource.ResultData
import com.tw.shared_resource.exception.Connectivity
import com.tw.shared_resource.exception.DataEmpty
import com.tw.shared_resource.exception.InvalidData
import com.tw.movielist.domain.LoadMovies
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
    private val movieLoader: com.tw.movielist.domain.LoadMovies
): ViewModel() {

    private val viewModelState = MutableStateFlow(
        MovieUiData(
            isLoading = true,
            failed = ""
        )
    )

    val moviesUiState = viewModelState
        .map(MovieUiData::toMoviesUiState)
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
                        is com.tw.shared_resource.ResultData.Success -> it.copy(
                            listMovies = result.data.toUiData(),
                            isLoading = false
                        )

                        is com.tw.shared_resource.ResultData.Failure -> it.copy(
                            failed = when (result.throwable) {
                                is com.tw.shared_resource.exception.Connectivity -> "Connectivity"
                                is com.tw.shared_resource.exception.InvalidData -> "Invalid Data"
                                is com.tw.shared_resource.exception.DataEmpty -> "Data Empty"
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
