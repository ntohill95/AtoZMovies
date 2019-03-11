package com.example.niamhtohill.atozmovies.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DatabaseMovie::class, DatabaseFavouriteMovies::class, DatabaseToWatchMovies::class], version = 1)
@TypeConverters(DatabaseMovieConverter::class, ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): DaoDatabaseMovie
    abstract fun favouritesDao(): DaoDatabaseFavouriteMovies

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}