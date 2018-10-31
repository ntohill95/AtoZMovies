package com.example.niamhtohill.atozmovies.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentTabBarBinding
import com.example.niamhtohill.atozmovies.databinding.FragmentTitleBarBinding

class TabBarFragment : Fragment(){

    private lateinit var viewModel: HomeViewModel

    private lateinit var ticketButton :ImageButton
    private lateinit var topRatedButton :ImageButton
    private lateinit var favouritesButton :ImageButton
    private lateinit var recommendationButton :ImageButton
    private lateinit var searchButton :ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView = inflater.inflate(R.layout.fragment_tab_bar, container, false)

        ticketButton = rootView.findViewById(R.id.tickets_button)
        topRatedButton = rootView.findViewById(R.id.top_rated_button)
        favouritesButton = rootView.findViewById(R.id.favourites_button)
        recommendationButton = rootView.findViewById(R.id.recommendations_button)
        searchButton = rootView.findViewById(R.id.search_button)

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentTabBarBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_bar, container, false)
        rootView = binding.root
//        binding.viewModel = viewModel

        return rootView
    }
}