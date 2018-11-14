package com.example.niamhtohill.atozmovies.home.cinemas

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.cell_cinema_film_showing.view.*

class CinemaShowingsViewholder(view: View) : RecyclerView.ViewHolder(view) {

    var movieShowingName = view.movie_showing_name_text_view
    var movieShowingTimes = view.movie_showing_times_text_view
}