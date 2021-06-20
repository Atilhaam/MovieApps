package com.ilham.movieapplication.core.data.source.remote

import android.util.Log
import com.ilham.movieapplication.core.data.source.remote.network.ApiService
import com.ilham.movieapplication.core.data.source.remote.response.MovieResponse
import com.ilham.movieapplication.core.data.source.remote.response.TvResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val apiService: ApiService) {


    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {

        return flow {
            try {
                val response = apiService.getNowPlaying()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    suspend fun getAllTvShow(): Flow<ApiResponse<List<TvResponse>>> {

        return flow {
            try {
                val response = apiService.getTvList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }



    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)

    }

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponse: List<TvResponse>)
    }
}