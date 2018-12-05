package com.example.niamhtohill.atozmovies.api

import java.io.Serializable

object Models {

    data class CineListResult(val postcode: String, val cinemas: List<CineListCinema>)
    data class CineListCinema(val distance: Float, val name: String, val id: String)

    data class MoviesApiCinema(val venue_id: String, val name: String, val address: String, val phone: String, val url: String, val distance: String)
    data class MoviesApiShowings(val title: String, val link: String, val time: List<String>)

    data class MoviesDBPopular(val page: Int, val total_results: Int, val total_pages: Int, val results: List<MoviesDBMovie>)
    data class MoviesDBMovie(
        val vote_count: Int,
        val id: Int,
        val video: Boolean,
        val vote_average: Float,
        val title: String,
        val popularity: Float,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val genre_ids: List<Int>,
        val backdrop_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String
    ) : Serializable
}