package com.example.niamhtohill.atozmovies.home.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.home.HomeActivity

class FavouritesFragment : Fragment() {

    private var fakeList = ArrayList<String>()
    lateinit var parentBaseActivity: HomeActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_favourites, container, false)
        val listView: RecyclerView = rootView.findViewById(R.id.favourites_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
            listView.adapter = FavouritesAdapter(context!!, generatedFakeData())

        return rootView
    }

    private fun generatedFakeData(): ArrayList<String> {
        fakeList.clear()
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