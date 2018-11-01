package com.example.niamhtohill.atozmovies.home.tickets

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
import com.example.niamhtohill.atozmovies.databinding.FragmentTicketsBinding
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.MyViewModelFactory

class TicketsFragment : Fragment(){

    private var fakeList = ArrayList<String>()
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentTicketsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tickets, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        val rootView = binding.root
        val listView: RecyclerView = rootView.findViewById(R.id.cinema_tickets_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        listView.adapter = TicketsAdapter(context!!, generatedFakeData())
        return rootView
    }

    private fun generatedFakeData(): ArrayList<String> {
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        return fakeList
    }
}