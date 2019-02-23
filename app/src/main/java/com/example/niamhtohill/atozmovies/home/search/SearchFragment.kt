package com.example.niamhtohill.atozmovies.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.home.HomeActivity

class SearchFragment : Fragment() {

    private var fakeList = ArrayList<String>()
    lateinit var parentBaseActivity: HomeActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)

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