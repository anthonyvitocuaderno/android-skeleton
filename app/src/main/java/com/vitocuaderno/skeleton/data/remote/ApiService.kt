package com.vitocuaderno.skeleton.data.remote

import com.vitocuaderno.skeleton.data.remote.models.Session
import com.vitocuaderno.skeleton.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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

    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("email") username: String,
        @Field("password") password: String
    ): Session
}
