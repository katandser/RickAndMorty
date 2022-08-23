package com.example.rickandmorty.api

import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Maybe
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface IServerApi {


    @GET("character")
    fun getAllEpisodes() : Maybe<Any>

    companion object {

        private const val TIMEOUT_CONNECT = 10L
        private const val TIMEOUT_RW = 1L
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        fun create(): IServerApi {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_RW, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
            return retrofit.create(IServerApi::class.java)
        }
    }
}