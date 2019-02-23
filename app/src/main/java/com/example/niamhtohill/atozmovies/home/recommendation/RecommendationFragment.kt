package com.example.niamhtohill.atozmovies.home.recommendation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.home.HomeActivity

class RecommendationFragment : Fragment() {

    private var fakeList = ArrayList<String>()
    lateinit var parentBaseActivity: HomeActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_recommendation, container, false)
        val listView: RecyclerView = rootView.findViewById(R.id.recommendations_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        listView.adapter = RecommendationAdapter(context!!, generatedFakeData())
        return rootView
    }

    private fun generatedFakeData(): ArrayList<String> {
        fakeList.clear()
        fakeList.add("Recommendation 1")
        fakeList.add("Recommendation 2")
        fakeList.add("Recommendation 3")
        fakeList.add("Recommendation 4")
        fakeList.add("Recommendation 5")
        fakeList.add("Recommendation 6")
        fakeList.add("Recommendation 7")
        fakeList.add("Recommendation 8")
        fakeList.add("Recommendation 9")
        fakeList.add("Recommendation 10")
        return fakeList
    }
}