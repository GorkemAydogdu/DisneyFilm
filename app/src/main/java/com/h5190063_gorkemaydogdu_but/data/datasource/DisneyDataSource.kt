package com.h5190063_gorkemaydogdu_but.data.datasource

import com.h5190063_gorkemaydogdu_but.data.model.DisneyResponse
import com.h5190063_gorkemaydogdu_but.util.Resource
import kotlinx.coroutines.flow.Flow

interface DisneyDataSource {
    fun filmleriGetir(): Flow<Resource<DisneyResponse>>
}