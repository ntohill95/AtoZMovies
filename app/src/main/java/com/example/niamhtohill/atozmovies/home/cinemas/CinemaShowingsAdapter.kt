package com.example.niamhtohill.atozmovies.home.cinemas

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models

class CinemaShowingsAdapter(private var context: Context, private val cinemaShowingsList: List<Models.MoviesApiShowings>) : RecyclerView.Adapter<CinemaShowingsViewholder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CinemaShowingsViewholder {
        return CinemaShowingsViewholder(LayoutInflater.from(context).inflate(R.layout.cell_cinema_film_showing, p0, false))
    }

    override fun getItemCount(): Int {
        return cinemaShowingsList.size
    }

    override fun onBindViewHolder(p0: CinemaShowingsViewholder, p1: Int) {
        p0.movieShowingName.text = cinemaShowingsList[p1].title
        p0.movieShowingTimes.text = cinemaShowingsList[p1].time.joinToString(", ")
    }
}