package com.h5190063_gorkemaydogdu_but.data.repository


import com.h5190063_gorkemaydogdu_but.data.datasource.DisneyDataSource
import com.h5190063_gorkemaydogdu_but.data.model.DisneyResponse
import com.h5190063_gorkemaydogdu_but.data.datasource.remote.RemoteDisneyRetrofitDataSource
import com.h5190063_gorkemaydogdu_but.util.Resource
import kotlinx.coroutines.flow.Flow

class DisneyRepository {
    private var disneyDataSource: DisneyDataSource? = null

    init {
        /*val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netinfo = cm.activeNetworkInfo
        if (netinfo != null && netinfo.isConnectedOrConnecting) {
            disneyDataSource = RemoteDisneyRetrofitDataSource()
        } else {
            disneyDataSource = LocalDisneyRetrofitDataSource()
        }*/
        disneyDataSource = RemoteDisneyRetrofitDataSource()
    }

    fun filmleriGetir(): Flow<Resource<DisneyResponse>> {
        return disneyDataSource!!.filmleriGetir()
    }
}