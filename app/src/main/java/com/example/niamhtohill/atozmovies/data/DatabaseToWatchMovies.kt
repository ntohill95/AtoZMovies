package com.example.niamhtohill.atozmovies.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "toWatchMovies")
data class DatabaseToWatchMovies(
    @PrimaryKey(autoGenerate = true)
    val tableId: Int,
    @TypeConverters(DatabaseMovieConverter::class)
    val movies: List<DatabaseMovie>
)