package com.example.niamhtohill.atozmovies.home.topMovies

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models

class TopMoviesAdapter(private var context: Context, private val topMovieList: List<Models.MoviesDBMovie>) : RecyclerView.Adapter<TopMoviesViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopMoviesViewHolder {
        return TopMoviesViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_top_movies, p0, false))
    }

    override fun getItemCount(): Int {
        return topMovieList.size
    }

    override fun onBindViewHolder(p0: TopMoviesViewHolder, p1: Int) {
        val movieTitle = topMovieList[p1]
        p0.topMovieTitle.text = movieTitle.title
        p0.topMovieRating.text = (p1 + 1).toString() + " ."
    }
}
