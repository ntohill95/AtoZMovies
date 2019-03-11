package com.example.niamhtohill.atozmovies.data

import androidx.room.*

@Dao
interface DaoDatabaseMovie {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: DatabaseMovie)

    @Update
    fun updateMovie(movie: DatabaseMovie)

    @Delete
    fun deleteMovie(movie: DatabaseMovie)

    @Query("SELECT * FROM DatabaseMovie WHERE movie_id == :movieId")
    fun getMovieById(movieId: Int): List<DatabaseMovie>

    @Query("SELECT * FROM DatabaseMovie")
    fun getMovies(): List<DatabaseMovie>
}