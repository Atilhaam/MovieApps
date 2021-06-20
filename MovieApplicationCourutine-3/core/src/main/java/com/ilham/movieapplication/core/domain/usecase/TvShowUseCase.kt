package com.ilham.movieapplication.core.domain.usecase

import com.ilham.movieapplication.core.domain.model.TvShow
import com.ilham.movieapplication.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {
    fun getAllTvShow(): Flow<Resource<List<TvShow>>>
    fun getLikedTvShow(): Flow<List<TvShow>>
    fun setTvShowLiked(tvShow: TvShow, state: Boolean)
}