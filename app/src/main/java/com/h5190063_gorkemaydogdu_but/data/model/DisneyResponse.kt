package com.h5190063_gorkemaydogdu_but.data.model

import com.google.gson.annotations.SerializedName

class DisneyResponse : ArrayList<DisneyResponseItem>()

data class DisneyResponseItem(
        @SerializedName("description")
        val description: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("playtime")
        val playtime: String,
        @SerializedName("plot")
        val plot: String,
        @SerializedName("poster")
        val poster: String,
        @SerializedName("release")
        val release: String
)