package com.example.niamhtohill.atozmovies.data

data class LocalMovie(val directory: String) {
    val movieId: Int = 0
    val vote_count: Int = 0
    val vote_average: Float = 0f
    val video: Boolean = false
    val title: String = ""
    val popularity: Float = 0f
    val poster_path: String = ""
    val original_language: String = ""
    val original_title: String = ""
    val genre_ids: List<Int> = ArrayList()
    val backdrop_path: String = ""
    val adult: Boolean = false
    val overview: String = ""
    val release_date: String = ""
}