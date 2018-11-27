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
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.API_KEY
import com.example.niamhtohill.atozmovies.databinding.FragmentTopMoviesBinding
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.MyViewModelFactory

class TopMoviesFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var fakeList = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentTopMoviesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_movies, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        val rootView = binding.root
        val listView: RecyclerView = rootView.findViewById(R.id.top_movies_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        viewModel.onPopularMoviesSearch(API_KEY)
        viewModel.listOPopularMovies.observe(this, Observer {
            listView.adapter = TopMoviesAdapter(context!!, viewModel.listOPopularMovies.value!!)
        })

        return rootView
    }
}