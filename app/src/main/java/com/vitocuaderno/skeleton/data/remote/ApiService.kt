package com.vitocuaderno.skeleton.data.remote

import com.vitocuaderno.skeleton.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {

    companion object {

        fun create(): ApiService {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

//    @GET("search")//?term=star&country=au&media=movie&all
//    suspend fun searchTracks(
//        @Query("term") term: String = "star",
//        @Query("country") country: String = "au",
//        @Query("media") media: String = "movie",
//        @Query("all") all: Boolean = true,
//    ): TrackSearchResponse
}
