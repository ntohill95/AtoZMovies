package com.example.niamhtohill.atozmovies.data

data class LocalList(val directory: String) {
    var listTitle: String = ""
    var moviesAssociated: List<LocalMovie> = ArrayList()
}