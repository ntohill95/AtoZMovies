package com.example.niamhtohill.atozmovies.home.favourites

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.data.DatabaseMovie

class FavouritesAdapter(private var context: Context, private val movieList: ArrayList<DatabaseMovie>) : RecyclerView.Adapter<FavouritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(LayoutInflater.from(context).inflate(R.layout.favourite_movies_cell, parent, false))
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        if (movieList.size != 0) {
            val itemString: String = movieList[position].title
            holder.movieTitleTextView.text = itemString
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}
