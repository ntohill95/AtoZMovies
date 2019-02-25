package com.example.niamhtohill.atozmovies.home.search

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.favourite_movies_cell.view.*

class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var movieTitleName = view.movie_title_textview
    var moviePoster = view.movie_image
    var movieGenre = view.movie_genre_textview
    var movieVoteAverage = view.movie_duration_textview
}