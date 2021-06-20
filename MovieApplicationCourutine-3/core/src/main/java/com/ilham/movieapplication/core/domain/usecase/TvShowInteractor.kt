package com.ilham.movieapplication.core.domain.usecase

import com.ilham.movieapplication.core.domain.model.TvShow
import com.ilham.movieapplication.core.domain.repository.ITvRepository

class TvShowInteractor(private val tvShowRepository: ITvRepository): TvShowUseCase {
    override fun getAllTvShow() = tvShowRepository.getAllTvShow()


    override fun getLikedTvShow() = tvShowRepository.getLikedTvShow()


    override fun setTvShowLiked(tvShow: TvShow, state: Boolean) = tvShowRepository.setTvShowLiked(tvShow, state)

}