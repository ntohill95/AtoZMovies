package com.example.niamhtohill.atozmovies.home.tickets

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models

class TicketsAdapter(private var context: Context, private val cinemaList: List<Models.Cinema>) : RecyclerView.Adapter<TicketsViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TicketsViewHolder {
        return TicketsViewHolder(LayoutInflater.from(context).inflate(R.layout.tickets_cell, p0, false))
    }

    override fun getItemCount(): Int {
        return cinemaList.size
    }

    override fun onBindViewHolder(p0: TicketsViewHolder, p1: Int) {
        val itemString: String = cinemaList[p1].name
        p0.cinemaNameTextView.text = itemString
    }
}