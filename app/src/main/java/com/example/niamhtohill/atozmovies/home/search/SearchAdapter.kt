package com.example.niamhtohill.atozmovies.home.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.data.databaseMovieToMoviesDBMovieMapper
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.movieDetail.MovieDetailActivity
import com.squareup.picasso.Picasso
import java.io.Serializable

class SearchAdapter(private var context: Context, private val movieList: List<Models.MoviesDBMovie>, private val viewModel: HomeViewModel) : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.favourite_movies_cell, p0, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(p0: SearchViewHolder, p1: Int) {
        val movie = movieList[p1]
        p0.movieTitleName.text = movie.title
        if (movie.poster_path != null) {
            Picasso.get().load("http://image.tmdb.org/t/p/w185" + movie.poster_path).into(p0.moviePoster)
        } else {
            p0.moviePoster.setImageDrawable(context.resources.getDrawable(R.drawable.no_image_available))
        }
        p0.movieVoteAverage.text = " | " + movie.vote_average.toString() + "/10"
        p0.movieGenre.text = viewModel.genresOfMovieSelected(movie.genre_ids).joinToString(", ")

        p0.itemView.setOnClickListener {
            val movieSelected = (movie)
            val bundle = Bundle()
            bundle.putSerializable("movieSelected", movieSelected as Serializable)
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }
}