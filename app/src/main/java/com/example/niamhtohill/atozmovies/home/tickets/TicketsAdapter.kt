package com.example.niamhtohill.atozmovies.home.tickets

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.cinemas.CinemasActivity

class TicketsAdapter(private var context: Context, private val cinemaList: List<Models.CineListCinema>, private val viewModel: HomeViewModel) : RecyclerView.Adapter<TicketsViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TicketsViewHolder {
        return TicketsViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_tickets, p0, false))
    }

    override fun getItemCount(): Int {
        return cinemaList.size
    }

    override fun onBindViewHolder(p0: TicketsViewHolder, p1: Int) {

        p0.cinemaNameTextView.text = cinemaList[p1].name
        p0.cinemasDistanceTextView.text = cinemaList[p1].distance.toString() + " miles |"
        p0.cinemaListCell.setOnClickListener {

            val intent = Intent(context, CinemasActivity::class.java)
            intent.putExtra("cinemaId", cinemaList[p1].id)
            intent.putExtra("cinemaName", cinemaList[p1].name)
            intent.putExtra("postcodeSearched", viewModel.searchedPostcode.value)
            mListener!!.cinemaSelected(cinemaList[p1].name)
            context.startActivity(intent)
        }
    }

    private var mListener: AdapterListener? = null

    fun setListener(listener: AdapterListener) {
        this.mListener = listener
    }

    interface AdapterListener {
        fun cinemaSelected(name: String)
    }
}
