package com.ilham.movieapplication.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val movieId: String,
    val title: String,
    val description: String,
    val language: String,
    val releaseDate: String,
    val liked: Boolean = false,
    val poster: String,
) : Parcelable