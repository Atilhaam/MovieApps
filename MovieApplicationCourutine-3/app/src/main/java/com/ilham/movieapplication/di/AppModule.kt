package com.ilham.movieapplication.di

import com.ilham.movieapplication.core.domain.usecase.MovieInteractor
import com.ilham.movieapplication.core.domain.usecase.MovieUseCase
import com.ilham.movieapplication.core.domain.usecase.TvShowInteractor
import com.ilham.movieapplication.core.domain.usecase.TvShowUseCase
import com.ilham.movieapplication.detail.DetailMovieViewModel
import com.ilham.movieapplication.detail.DetailTvViewModel
import com.ilham.movieapplication.movie.MovieViewModel
import com.ilham.movieapplication.tv.TvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
}
val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvViewModel(get()) }

}