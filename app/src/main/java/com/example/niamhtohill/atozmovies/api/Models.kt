package com.example.niamhtohill.atozmovies.api

object Models {

    data class CineListResult(val postcode: String, val cinemas: List<CineListCinema>)
    data class CineListCinema(val distance: Float, val name: String, val id: String)

    data class MoviesApiCinema(val venue_id: String, val name: String, val address: String, val phone: String, val url: String, val distance: String)
    data class MoviesApiShowings(val title: String, val link: String, val time: List<String>)
}