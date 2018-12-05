package com.example.niamhtohill.atozmovies.home.topMovies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.API_KEY
import com.example.niamhtohill.atozmovies.databinding.FragmentPopularBinding
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.MyViewModelFactory

class PopularMoviesFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var progressSpinner: ProgressBar
    private lateinit var listView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentPopularBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        val rootView = binding.root
        listView = rootView.findViewById(R.id.top_movies_list_view)
        progressSpinner = rootView.findViewById(R.id.loading_spinner_popular)
        progressSpinner.visibility = View.VISIBLE
        listView.layoutManager = LinearLayoutManager(this.context)
        viewModel.onPopularMoviesSearch(API_KEY)
        viewModel.listOPopularMovies.observe(this, Observer {
            listView.adapter = PopularMoviesAdapter(context!!, viewModel.listOPopularMovies.value!!, viewModel)
            progressSpinner.visibility = View.INVISIBLE
        })

        return rootView
    }
}