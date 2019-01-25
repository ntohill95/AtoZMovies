package com.example.niamhtohill.atozmovies.home.cinemas

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.niamhtohill.atozmovies.R

class CinemaShowingsFragment : Fragment() {

    private lateinit var cinemaShowingsListView: RecyclerView
    private lateinit var progressSpinner: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parentActivity = activity as CinemasActivity
        val rootView = inflater.inflate(R.layout.fragment_cinema_showings, container, false)

        progressSpinner = rootView.findViewById(R.id.loading_spinner_showings)
        cinemaShowingsListView = rootView.findViewById(R.id.cinema_showings_listview)
        cinemaShowingsListView.layoutManager = LinearLayoutManager(this.context)

        parentActivity.viewModel.showingsFetched.observe(this, Observer {
            if (parentActivity.viewModel.showingsFetched.value!!) {
                val showingsAdapter = CinemaShowingsAdapter(context!!, parentActivity.viewModel.listOfShowings.value!!)
                progressSpinner.visibility = View.GONE
                cinemaShowingsListView.adapter = showingsAdapter
            } else {
                progressSpinner.visibility = View.VISIBLE
            }
        })
        return rootView
    }
}