package com.ilham.movieapplication.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ilham.movieapplication.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val getMovies = movieUseCase.getAllMovies().asLiveData()
}