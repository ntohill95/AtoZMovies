package com.example.niamhtohill.atozmovies.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R

class SearchAdapter(private var context: Context, private val movieList: ArrayList<String>) : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.favourite_movies_cell, p0, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(p0: SearchViewHolder, p1: Int) {
        val movieTitle = movieList[p1]
        p0.movieTitleName.text = movieTitle
    }

}