package com.ilham.movieapplication.core.data.source.local

import androidx.lifecycle.LiveData
import com.ilham.movieapplication.core.data.source.local.entity.MovieEntity
import com.ilham.movieapplication.core.data.source.local.entity.TvShowEntity
import com.ilham.movieapplication.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieDao: MovieDao) {


    fun getAllMovies(): Flow<List<MovieEntity>> = mMovieDao.getMovies()

    fun getAllTvShow(): Flow<List<TvShowEntity>> = mMovieDao.getTvShow()

    fun getLikedMovies(): Flow<List<MovieEntity>> = mMovieDao.getLikedMovie()

    fun getLikedTvShow(): Flow<List<TvShowEntity>> = mMovieDao.getLikedTvShow()

    fun getMovie(movieId: String) : LiveData<MovieEntity> =
        mMovieDao.getMoviesById(movieId)

    fun getTv(tvId: String) : LiveData<TvShowEntity> =
        mMovieDao.getTvById(tvId)

    suspend fun insertTvShow(tv: List<TvShowEntity>) = mMovieDao.insertTvShow(tv)

   suspend fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovies(movies)

    fun setMovieLiked(movie: MovieEntity, newState: Boolean) {
        movie.liked = newState
        mMovieDao.upDateMovie(movie)
    }

    fun setTvLiked(tv: TvShowEntity, newState: Boolean) {
        tv.liked = newState
        mMovieDao.updateTvShow(tv)
    }
}