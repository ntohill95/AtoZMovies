package com.example.niamhtohill.atozmovies.api

object Models {

    data class Result(val postcode: String, val cinemas : List<Cinema>)
    data class Cinema(val distance: Float, val name: String, val id: String)
}