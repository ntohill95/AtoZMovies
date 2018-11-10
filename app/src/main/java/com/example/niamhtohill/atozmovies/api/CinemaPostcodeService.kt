package com.example.niamhtohill.atozmovies.api

import com.example.niamhtohill.atozmovies.BuildConfig
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor

interface CinemaPostcodeService {

    @GET(POSTCODE)
    fun fetchCinemasPostcode(@Path("postcode") postcode: String): Single<Models.Result>

    companion object {
        fun create(): CinemaPostcodeService {
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://api.cinelist.co.uk")
                    .build()
            return retrofit.create(CinemaPostcodeService::class.java)
        }
    }
}

val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}

val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()
