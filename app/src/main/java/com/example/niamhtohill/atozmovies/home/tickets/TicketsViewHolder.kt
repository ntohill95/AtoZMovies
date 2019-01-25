package com.example.niamhtohill.atozmovies.home.tickets

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.cell_tickets.view.*

class TicketsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var cinemaNameTextView = view.cinema_name_textview
    var cinemasDistanceTextView = view.cinema_distance_textview
    var cinemaListCell = view.ticket_cell_holder
}