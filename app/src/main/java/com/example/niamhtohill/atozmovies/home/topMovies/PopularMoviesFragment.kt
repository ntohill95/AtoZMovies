package com.example.niamhtohill.atozmovies.home.topMovies

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
import com.example.niamhtohill.atozmovies.api.API_KEY
import com.example.niamhtohill.atozmovies.home.HomeActivity

class PopularMoviesFragment : Fragment() {

    lateinit var parentBaseActivity: HomeActivity
    private lateinit var progressSpinner: ProgressBar
    private lateinit var listView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_popular, container, false)
        listView = rootView.findViewById(R.id.top_movies_list_view)
        progressSpinner = rootView.findViewById(R.id.loading_spinner_popular)
        progressSpinner.visibility = View.VISIBLE
        listView.layoutManager = LinearLayoutManager(this.context)
        parentBaseActivity.viewModel.onPopularMoviesSearch(API_KEY)
        parentBaseActivity.viewModel.fetchGenreNames(API_KEY)
        parentBaseActivity.viewModel.listOPopularMovies.observe(this, Observer {
            listView.adapter = PopularMoviesAdapter(context!!, parentBaseActivity.viewModel.listOPopularMovies.value!!, parentBaseActivity.viewModel)
            progressSpinner.visibility = View.INVISIBLE
        })

        return rootView
    }
}