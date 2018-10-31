package com.example.niamhtohill.atozmovies.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R

class FavouritesAdapter(private var context: Context, private val movieList: ArrayList<String>) : RecyclerView.Adapter<FavouritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(LayoutInflater.from(context).inflate(R.layout.favourite_movies_cell, parent, false))
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val itemString: String = movieList[position]
        holder.movieTitleTextView.text = itemString
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}