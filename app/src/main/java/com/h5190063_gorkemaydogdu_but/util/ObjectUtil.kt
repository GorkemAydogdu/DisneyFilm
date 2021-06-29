package com.h5190063_gorkemaydogdu_but.util

import com.google.gson.Gson
import com.h5190063_gorkemaydogdu_but.data.model.DisneyResponseItem

object ObjectUtil {
    fun filmToJsonString(filmler: DisneyResponseItem?): String? {
        val gson = Gson()
        return gson.toJson(filmler)
    }

    fun jsonStringToFilm(jsonString: String?): DisneyResponseItem? {
        val gson = Gson()
        return gson.fromJson(jsonString, DisneyResponseItem::class.java)
    }
}