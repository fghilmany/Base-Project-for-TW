package com.tw.baseproject.feature.moviedetail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.baseproject.core.shared_resource.ResultData
import com.tw.baseproject.core.shared_resource.exception.Connectivity
import com.tw.baseproject.core.shared_resource.exception.DataEmpty
import com.tw.baseproject.core.shared_resource.exception.InvalidData
import com.tw.baseproject.app.factories.di.ViewModelFactory
import com.tw.baseproject.feature.moviedetail.domain.DetailMovie
import com.tw.baseproject.feature.moviedetail.domain.LoadDetailMovie
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ViewModelFactory::class)
class DetailMovieViewModel @AssistedInject constructor(
    private val useCase: LoadDetailMovie,
    @Assisted val movieId: Int
): ViewModel() {

    private val viewModelState = MutableStateFlow(
        DetailMovieViewModelState(
            isLoading = true,
            failed = ""
        )
    )

    val moviesUiState = viewModelState
        .map(DetailMovieViewModelState::toDetailMovieUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value.toDetailMovieUiState()
        )

    init {
        loadDetailMovie()
    }

    fun loadDetailMovie() {
        viewModelScope.launch {
            viewModelState.collect{
                useCase.loadDetailMovie(movieId).collect { result ->
                    Log.d("loadDetailMovie", "$result")
                    viewModelState.update {
                        when (result) {
                            is ResultData.Success -> it.copy(
                                detailMovie = result.data,
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


}


sealed interface DetailMovieUiState {
    val isLoading: Boolean
    val failed: String
    data class HasDetailMovie(
        override val isLoading: Boolean,
        val detailMovie: DetailMovie,
        override val failed: String
    ) : DetailMovieUiState

    data class NoDetailMovie(
        override val isLoading: Boolean,
        override val failed: String,
    ) : DetailMovieUiState
}

data class DetailMovieViewModelState(
    val movieId: Int? = null,
    val isLoading: Boolean = false,
    val detailMovie: DetailMovie? = null,
    val failed: String = ""
) {
    fun toDetailMovieUiState(): DetailMovieUiState =
        if (detailMovie == null) {
            DetailMovieUiState.NoDetailMovie(
                isLoading = isLoading,
                failed = failed
            )

        } else {
            DetailMovieUiState.HasDetailMovie(
                isLoading = isLoading,
                detailMovie = detailMovie,
                failed = failed
            )
        }
}
