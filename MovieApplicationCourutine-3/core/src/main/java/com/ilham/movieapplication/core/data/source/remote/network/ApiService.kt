package com.ilham.movieapplication.core.data.source.remote.network

import com.ilham.movieapplication.core.BuildConfig
import com.ilham.movieapplication.core.data.source.remote.response.ListMovieResponse
import com.ilham.movieapplication.core.data.source.remote.response.ListTvResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing?api_key=7a0390056fc89dd647a1702177f5adab")
    suspend fun getNowPlaying(): ListMovieResponse

    @GET("tv/on_the_air?api_key=7a0390056fc89dd647a1702177f5adab")
    suspend fun getTvList(): ListTvResponse
}
//https://api.themoviedb.org/3/movie/now_playing?api_key=7a0390056fc89dd647a1702177f5adab