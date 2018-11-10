package com.example.niamhtohill.atozmovies.home.recommendation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R

class RecommendationAdapter(private var context: Context, private val movieList: ArrayList<String>) : RecyclerView.Adapter<RecommendationViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecommendationViewHolder {
        return RecommendationViewHolder(LayoutInflater.from(context).inflate(R.layout.favourite_movies_cell, p0, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(p0: RecommendationViewHolder, p1: Int) {
        val itemString: String = movieList[p1]
        p0.movieTitleTextView.text = itemString }
}