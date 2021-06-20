package com.ilham.movieapplication.core.utils

import com.ilham.movieapplication.core.data.source.local.entity.MovieEntity
import com.ilham.movieapplication.core.data.source.local.entity.TvShowEntity
import com.ilham.movieapplication.core.data.source.remote.response.MovieResponse
import com.ilham.movieapplication.core.data.source.remote.response.TvResponse
import com.ilham.movieapplication.core.domain.model.Movies
import com.ilham.movieapplication.core.domain.model.TvShow

object DataMapper {
    fun mapMovieResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                movieId = it.movieId,
                title = it.title,
                description = it.description,
                language = it.language,
                releaseDate = it.releaseDate,
                liked = false,
                poster = it.poster
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movies> =
        input.map {
            Movies(
                movieId = it.movieId,
                title = it.title,
                description = it.description,
                language = it.language,
                releaseDate = it.releaseDate,
                liked = it.liked,
                poster = it.poster
            )
        }

    fun mapMovieDomainToEntity(input: Movies) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        description = input.description,
        language = input.language,
        releaseDate = input.releaseDate,
        liked = input.liked,
        poster = input.poster
    )

    fun mapTvResponseToEntities(input: List<TvResponse>): List<TvShowEntity> {
        val tvlist = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                tvId = it.tvId,
                title = it.title,
                description = it.description,
                language = it.language,
                releaseDate = it.releaseDate,
                liked = false,
                poster = it.poster
            )
            tvlist.add(tvShow)

        }
        return tvlist
    }

    fun mapTvEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                tvId = it.tvId,
                title = it.title,
                description = it.description,
                language = it.language,
                releaseDate = it.releaseDate,
                liked = it.liked,
                poster = it.poster
            )
        }

    fun mapTvDomainToEntity(input: TvShow) = TvShowEntity(
        tvId = input.tvId,
        title = input.title,
        description = input.description,
        language = input.language,
        releaseDate = input.releaseDate,
        liked = input.liked,
        poster = input.poster
    )


}