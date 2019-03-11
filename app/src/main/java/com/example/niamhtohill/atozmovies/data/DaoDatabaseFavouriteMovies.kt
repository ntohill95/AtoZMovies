package com.example.niamhtohill.atozmovies.data

import androidx.room.*

@Dao
interface DaoDatabaseFavouriteMovies {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(movie: DatabaseFavouriteMovies)

    @Update
    fun updateFavourites(databaseFavouriteMovies: DatabaseFavouriteMovies)

    @Delete
    fun deleteDatabaseFavourites(databaseFavouriteMovies: DatabaseFavouriteMovies)

    @Query("SELECT * FROM favouriteMovies WHERE tableId == :tableId")
    fun getTableById(tableId: Int): List<DatabaseFavouriteMovies>
}