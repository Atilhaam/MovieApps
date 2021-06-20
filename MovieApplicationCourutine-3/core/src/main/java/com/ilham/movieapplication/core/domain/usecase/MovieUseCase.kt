package com.ilham.movieapplication.core.domain.usecase

import com.ilham.movieapplication.core.domain.model.Movies
import com.ilham.movieapplication.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(): Flow<Resource<List<Movies>>>
    fun getLikedMovies(): Flow<List<Movies>>
    fun setMoviesLiked(movies: Movies, state: Boolean)
}