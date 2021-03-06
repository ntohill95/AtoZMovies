package com.example.niamhtohill.atozmovies.api

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaPostcodeService {

    @GET(POSTCODE_CINELIST)
    fun fetchCinemasPostcode(@Path("postcode") postcode: String): Single<Models.CineListResult>

    companion object {
        fun create(): CinemaPostcodeService {
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl("http://www.cinelist.co.uk/")
                    .build()
            return retrofit.create(CinemaPostcodeService::class.java)
        }
    }
}
