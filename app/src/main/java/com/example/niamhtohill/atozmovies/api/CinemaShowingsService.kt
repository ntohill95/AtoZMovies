package com.example.niamhtohill.atozmovies.api

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaShowingsService {

    @GET(CINEMA_SHOWINGS_MOVIEAPI)
    fun fetchCinemaShowings(@Path("venue_id") venueId: String): Single<List<Models.MoviesApiShowings>>

    @GET(CINEMAS_MOVIEAPI)
    fun fetchMoviesApiCinemasPostcode(@Path("postcode") postcode:String): Single<List<Models.MoviesApiCinema>>

    companion object {
        fun create(): CinemaShowingsService {
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("http://moviesapi.herokuapp.com/")
                    .build()
            return retrofit.create(CinemaShowingsService::class.java)
        }
    }
}
