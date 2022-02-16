package com.vitocuaderno.skeleton.data.remote

import com.vitocuaderno.skeleton.data.remote.models.Session
import com.vitocuaderno.skeleton.data.remote.models.User
import com.vitocuaderno.skeleton.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object {

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
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
    @POST("api/register")
    suspend fun register(
        @Field("email") username: String,
        @Field("password") password: String
    ): Session

    @FormUrlEncoded
    @POST("api/login")
    suspend fun login(
        @Field("email") username: String,
        @Field("password") password: String
    ): Session

    @GET("api/users/{id}")
    suspend fun getUser(
        @Field("id") id: String
    ): User
}
