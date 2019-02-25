package com.example.niamhtohill.atozmovies.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.home.HomeActivity

class SearchFragment : Fragment() {

    lateinit var parentBaseActivity: HomeActivity
    private lateinit var searchView: SearchView
    private lateinit var progressSpinner: ProgressBar
    private lateinit var noSearchResultsTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        searchView = rootView.findViewById(R.id.search_view)
        progressSpinner = rootView.findViewById(R.id.loading_spinner_search)
        noSearchResultsTextView = rootView.findViewById(R.id.no_search_found_text_view)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val queryFormatted = query!!.replace(" ", "+")
                parentBaseActivity.viewModel.onSearchFilm(queryFormatted)
                progressSpinner.visibility = View.VISIBLE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        parentBaseActivity.viewModel.searchResults.observe(this, Observer {
            progressSpinner.visibility = View.INVISIBLE
            noSearchResultsTextView.visibility = View.INVISIBLE
            val listView: RecyclerView = rootView.findViewById(R.id.search_list_view)
            listView.layoutManager = LinearLayoutManager(this.context)
            listView.adapter = SearchAdapter(context!!, parentBaseActivity.viewModel.searchResults.value!!, parentBaseActivity.viewModel)

            if (parentBaseActivity.viewModel.searchResults.value!!.isEmpty()) {
                noSearchResultsTextView.visibility = View.VISIBLE
                progressSpinner.visibility = View.INVISIBLE
            }
        })
        return rootView
    }
}