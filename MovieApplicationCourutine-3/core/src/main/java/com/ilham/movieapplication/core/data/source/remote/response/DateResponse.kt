package com.ilham.movieapplication.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DateResponse (
    @field:SerializedName("maximum")
    var maximum: String,
    @field:SerializedName("minimum")
    var minimum: String,
        )