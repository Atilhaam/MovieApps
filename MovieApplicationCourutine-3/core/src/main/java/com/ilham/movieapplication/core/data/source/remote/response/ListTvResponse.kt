package com.ilham.movieapplication.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvResponse (
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("results")
    val results: List<TvResponse>
        )