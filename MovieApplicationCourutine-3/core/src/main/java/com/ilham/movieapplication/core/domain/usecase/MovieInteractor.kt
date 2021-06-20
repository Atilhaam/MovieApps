package com.ilham.movieapplication.core.domain.usecase

import com.ilham.movieapplication.core.domain.model.Movies
import com.ilham.movieapplication.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovies() = movieRepository.getAllMovies()


    override fun getLikedMovies() = movieRepository.getLikedMovies()


    override fun setMoviesLiked(movies: Movies, state: Boolean) =  movieRepository.setMoviesLiked(movies, state)


}