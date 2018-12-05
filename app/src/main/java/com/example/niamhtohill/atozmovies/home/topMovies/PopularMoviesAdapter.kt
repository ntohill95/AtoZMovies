package com.example.niamhtohill.atozmovies.home.topMovies

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import java.io.Serializable

class PopularMoviesAdapter(private var context: Context, private val topMovieList: List<Models.MoviesDBMovie>, private val viewModel: HomeViewModel) : RecyclerView.Adapter<PopularMoviesViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_popular, p0, false))
    }

    override fun getItemCount(): Int {
        return topMovieList.size
    }

    override fun onBindViewHolder(p0: PopularMoviesViewHolder, p1: Int) {
        val movieTitle = topMovieList[p1]
        p0.topMovieTitle.text = movieTitle.title
        p0.topMovieRating.text = (p1 + 1).toString() + " ."
        p0.itemView.setOnClickListener {
            val movieSelected = viewModel.listOPopularMovies.value!![p1]
            val movieDetailFragment = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putSerializable("movieSelected", movieSelected as Serializable)
            movieDetailFragment.arguments = bundle
            val activity = context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.central_content_placeholder, movieDetailFragment).commit()
        }
    }

}
