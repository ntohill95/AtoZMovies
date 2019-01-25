package com.example.niamhtohill.atozmovies.home.search

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentSearchBinding
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.MyViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private var fakeList = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        val rootView = binding.root
        val listView: RecyclerView = rootView.findViewById(R.id.search_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        listView.adapter = SearchAdapter(context!!, generatedFakeData())
        return rootView
    }

    private fun generatedFakeData(): ArrayList<String> {
        fakeList.clear()
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