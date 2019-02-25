package com.example.niamhtohill.atozmovies.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.niamhtohill.atozmovies.R

class TabBarFragment : Fragment() {

    private lateinit var tabBarClickable: TabBarClickable

    lateinit var ticketButton: ImageButton
    lateinit var topRatedButton: ImageButton
    private lateinit var favouritesButton: ImageButton
    private lateinit var recommendationButton: ImageButton
    private lateinit var searchButton: ImageButton
    lateinit var parentBaseActivity: HomeActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_tab_bar, container, false)
        ticketButton = rootView.findViewById(R.id.tickets_button)
        ticketButton.setOnClickListener() {
            tabBarClickable.ticketButtonClicked()
            setImagesToNotSelected()
            ticketButton.setImageDrawable(resources.getDrawable(R.drawable.ticket_selected))
        }
        topRatedButton = rootView.findViewById(R.id.top_rated_button)
        topRatedButton.setOnClickListener {
            tabBarClickable.topMovieButtonClicked()
            setImagesToNotSelected()
            topRatedButton.setImageDrawable(resources.getDrawable(R.drawable.top_films_selected))
        }
        favouritesButton = rootView.findViewById(R.id.favourites_button)
        favouritesButton.setOnClickListener() {
            tabBarClickable.favouriteButtonClicked()
            setImagesToNotSelected()
            favouritesButton.setImageDrawable(resources.getDrawable(R.drawable.star_selected))
        }
        recommendationButton = rootView.findViewById(R.id.recommendations_button)
        recommendationButton.setOnClickListener {
            tabBarClickable.recommendationButtonClicked()
            setImagesToNotSelected()
            recommendationButton.setImageDrawable(resources.getDrawable(R.drawable.recommendation_selected))
        }
        searchButton = rootView.findViewById(R.id.search_button)
        searchButton.setOnClickListener {
            tabBarClickable.searchButtonClicked()
            setImagesToNotSelected()
            searchButton.setImageDrawable(resources.getDrawable(R.drawable.search_selected))
        }
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TabBarClickable) {
            tabBarClickable = context
        } else {
            throw ClassCastException(context.toString())
        }
    }

    fun setImagesToNotSelected(){
        ticketButton.setImageDrawable(resources.getDrawable(R.drawable.ticket_not))
        topRatedButton.setImageDrawable(resources.getDrawable(R.drawable.top_films_not))
        favouritesButton.setImageDrawable(resources.getDrawable(R.drawable.star_not))
        recommendationButton.setImageDrawable(resources.getDrawable(R.drawable.recommendation_not))
        searchButton.setImageDrawable(resources.getDrawable(R.drawable.search_not))
    }
}


interface TabBarClickable {
    fun ticketButtonClicked()
    fun favouriteButtonClicked()
    fun topMovieButtonClicked()
    fun recommendationButtonClicked()
    fun searchButtonClicked()
}