package com.ilham.movieapplication.core.data

import com.ilham.movieapplication.core.data.source.local.LocalDataSource
import com.ilham.movieapplication.core.data.source.remote.ApiResponse
import com.ilham.movieapplication.core.data.source.remote.RemoteDataSource
import com.ilham.movieapplication.core.data.source.remote.response.MovieResponse
import com.ilham.movieapplication.core.domain.model.Movies
import com.ilham.movieapplication.core.domain.repository.IMovieRepository
import com.ilham.movieapplication.core.utils.AppExecutors
import com.ilham.movieapplication.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors) : IMovieRepository{

    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map {  DataMapper.mapMovieEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponseToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()



        override fun getLikedMovies(): Flow<List<Movies>> {
            return localDataSource.getLikedMovies().map {  DataMapper.mapMovieEntitiesToDomain(it) }


        }

        override fun setMoviesLiked(movie: Movies, state: Boolean) {
            val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
            appExecutors.diskIO().execute { localDataSource.setMovieLiked(movieEntity, state) }
        }
    }
