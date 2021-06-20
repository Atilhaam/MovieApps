package com.ilham.movieapplication.favorite.di

import com.ilham.movieapplication.favorite.movie.MovieFavoriteViewModel
import com.ilham.movieapplication.favorite.tv.TvFavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { MovieFavoriteViewModel(get()) }
    viewModel { TvFavoriteViewModel(get()) }
}