package com.example.niamhtohill.atozmovies.api

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDatabaseService {

    @GET(MOVIES_POPULAR)
    fun fetchPopularMovies(@Query("api_key") api_key: String): Single<Models.MoviesDBPopular>

    companion object {
        fun create(): MovieDatabaseService {
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("https://api.themoviedb.org/")
                    .build()
            return retrofit.create(MovieDatabaseService::class.java)
        }
    }
}