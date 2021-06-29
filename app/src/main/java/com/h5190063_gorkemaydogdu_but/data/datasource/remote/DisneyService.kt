package com.h5190063_gorkemaydogdu_but.data.datasource.remote

import com.h5190063_gorkemaydogdu_but.data.model.DisneyResponse
import com.h5190063_gorkemaydogdu_but.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DisneyService {
    //https://gist.githubusercontent.com/skydoves/aa3bbbf495b0fa91db8a9e89f34e4873/raw/a1a13d37027e8920412da5f00f6a89c5a3dbfb9a/DisneyPosters.json
    @GET("DisneyPosters.json")
    suspend fun filmleriGetir(): Response<DisneyResponse>

    companion object {

        fun build(): DisneyService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHtppClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .client(okHtppClient)
                    .build()
            return retrofit.create(DisneyService::class.java)
        }
    }
}