package com.example.niamhtohill.atozmovies.home.topMovies

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.cell_top_movies.view.*

class TopMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var topMovieTitle = view.top_movie_textview
    var topMovieRating = view.top_movie_rating_textview
}