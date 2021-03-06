package com.example.niamhtohill.atozmovies.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.niamhtohill.atozmovies.api.Models

@Entity
data class DatabaseMovie(
    @PrimaryKey(autoGenerate = true)
    val movie_id: Int? = null,
    val vote_count: Int,
    val vote_average: Float,
    val video: Boolean,
    val title: String,
    val popularity: Float,
    val poster_path: String,
    val original_language: String,
    val original_title: String,
    @TypeConverters(ListConverter::class)
    val genre_ids: List<Int>,
    val backdrop_path: String,
    val adult: Boolean,
    val overview: String,
    val release_date: String
)

fun databaseMovieToMoviesDBMovieMapper(databaseMovie: DatabaseMovie): Models.MoviesDBMovie {
    return Models.MoviesDBMovie(
            databaseMovie.vote_count,
            databaseMovie.movie_id ?: 0,
            databaseMovie.video,
            databaseMovie.vote_average,
            databaseMovie.title,
            databaseMovie.popularity,
            databaseMovie.poster_path,
            databaseMovie.original_language,
            databaseMovie.original_title,
            databaseMovie.genre_ids,
            databaseMovie.backdrop_path,
            databaseMovie.adult,
            databaseMovie.overview,
            databaseMovie.release_date)
}