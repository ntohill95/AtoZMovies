package com.example.niamhtohill.atozmovies.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentFavouritesBinding
import com.example.niamhtohill.atozmovies.databinding.FragmentTabBarBinding

class FavouritesFragment :Fragment(){

    private var fakeList = ArrayList<String>()
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentFavouritesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)
        val rootView = binding.root

        val listView : RecyclerView = rootView.findViewById(R.id.favourites_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        listView.adapter = FavouritesAdapter(context!!, generatedFakeData())
        return rootView
    }

    private fun generatedFakeData(): ArrayList<String> {
        fakeList.add("Movie Title 1")
        fakeList.add("Movie Title 2")
        fakeList.add("Movie Title 3")
        fakeList.add("Movie Title 4")
        fakeList.add("Movie Title 5")
        fakeList.add("Movie Title 6")
        fakeList.add("Movie Title 7")
        fakeList.add("Movie Title 8")
        fakeList.add("Movie Title 9")
        fakeList.add("Movie Title 10")
        return fakeList
    }
}