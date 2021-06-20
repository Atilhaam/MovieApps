package com.ilham.movieapplication.detail

import androidx.lifecycle.ViewModel
import com.ilham.movieapplication.core.domain.model.Movies
import com.ilham.movieapplication.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movies, newStatus: Boolean) = movieUseCase.setMoviesLiked(movie, newStatus)


}