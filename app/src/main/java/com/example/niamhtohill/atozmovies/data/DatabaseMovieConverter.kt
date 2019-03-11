package com.example.niamhtohill.atozmovies.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseMovieConverter {
    @TypeConverter
    fun fromMovieList(movies: List<DatabaseMovie>): String {
        val gson = Gson()
        val type = object : TypeToken<List<DatabaseMovie>>() {}.type
        return gson.toJson(movies, type)
    }

    @TypeConverter
    fun toMovieList(gsonString: String): List<DatabaseMovie> {
        val gson = Gson()
        val type = object : TypeToken<List<DatabaseMovie>>() {}.type
        return gson.fromJson(gsonString, type)
    }
}