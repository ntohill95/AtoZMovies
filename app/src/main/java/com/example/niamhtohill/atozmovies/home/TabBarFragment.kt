package com.example.niamhtohill.atozmovies.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentTabBarBinding

class TabBarFragment : Fragment() {

    private lateinit var tabBarClickable: TabBarClickable
    private lateinit var viewModel: HomeViewModel

     lateinit var ticketButton: ImageButton
     lateinit var topRatedButton: ImageButton
    private lateinit var favouritesButton: ImageButton
    private lateinit var recommendationButton: ImageButton
    private lateinit var searchButton: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentTabBarBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_bar, container, false)
        binding.setVariable(BR.viewModel, viewModel)

        val rootView = binding.root
        ticketButton = rootView.findViewById(R.id.tickets_button)
        ticketButton.setOnClickListener(){
            tabBarClickable.ticketButtonClicked()
        }
        topRatedButton = rootView.findViewById(R.id.top_rated_button)
        topRatedButton.setOnClickListener{
            tabBarClickable.topMovieButtonClicked()
        }
        favouritesButton = rootView.findViewById(R.id.favourites_button)
        favouritesButton.setOnClickListener(){
            tabBarClickable.favouriteButtonClicked()
        }
        recommendationButton = rootView.findViewById(R.id.recommendations_button)
        recommendationButton.setOnClickListener{
            tabBarClickable.recommendationButtonClicked()
        }
        searchButton = rootView.findViewById(R.id.search_button)
        searchButton.setOnClickListener{
            tabBarClickable.searchButtonClicked()
        }
        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is TabBarClickable) {
            tabBarClickable = context
        } else {
            throw ClassCastException(context.toString())
        }
    }
}

interface TabBarClickable {
    fun ticketButtonClicked()
    fun favouriteButtonClicked()
    fun topMovieButtonClicked()
    fun recommendationButtonClicked()
    fun searchButtonClicked()
}