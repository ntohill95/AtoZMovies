package com.example.niamhtohill.atozmovies.api

object Models {

    data class Result(val query: String)
    data class Cinema(val distance: Float, val name: String, val cinema_id: String)
}