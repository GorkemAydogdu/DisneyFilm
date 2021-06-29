package com.h5190063_gorkemaydogdu_but.data.datasource.remote

import com.h5190063_gorkemaydogdu_but.data.datasource.DisneyDataSource
import com.h5190063_gorkemaydogdu_but.data.model.DisneyResponse
import com.h5190063_gorkemaydogdu_but.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDisneyRetrofitDataSource : DisneyDataSource {
    override fun filmleriGetir(): Flow<Resource<DisneyResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = DisneyService.build().filmleriGetir()

            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}