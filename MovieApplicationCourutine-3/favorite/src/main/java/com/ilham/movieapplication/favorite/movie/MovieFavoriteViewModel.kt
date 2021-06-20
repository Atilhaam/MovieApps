package com.ilham.movieapplication.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ilham.movieapplication.core.domain.usecase.MovieUseCase

class MovieFavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovie = movieUseCase.getLikedMovies().asLiveData()
}