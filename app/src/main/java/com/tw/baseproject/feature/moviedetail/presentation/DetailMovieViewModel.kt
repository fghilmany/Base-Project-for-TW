package com.tw.baseproject.feature.moviedetail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.shared_resource.ResultData
import com.tw.shared_resource.exception.Connectivity
import com.tw.shared_resource.exception.DataEmpty
import com.tw.shared_resource.exception.InvalidData
import com.tw.baseproject.app.factories.di.ViewModelFactory
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
        DetailMovieUiData(
            isLoading = true,
            failed = ""
        )
    )

    val moviesUiState = viewModelState
        .map(DetailMovieUiData::toDetailMovieUiState)
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
                            is com.tw.shared_resource.ResultData.Success -> it.copy(
                                detailMovie = result.data.toUiData(),
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


}