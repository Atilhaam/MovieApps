package com.ilham.movieapplication.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse (
    @field:SerializedName("id")
    var movieId: String,

    @field:SerializedName("original_title")
    var title: String,

    @field:SerializedName("overview")
    var description: String,

    @field:SerializedName("original_language")
    var language: String,

    @field:SerializedName("release_date")
    var releaseDate: String,

    @field:SerializedName("poster_path")
    var poster: String
        ) : Parcelable