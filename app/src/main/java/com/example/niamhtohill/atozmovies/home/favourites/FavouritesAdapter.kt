package com.example.niamhtohill.atozmovies.home.favourites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.data.DatabaseMovie
import com.example.niamhtohill.atozmovies.data.databaseMovieToMoviesDBMovieMapper
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.movieDetail.MovieDetailActivity
import com.squareup.picasso.Picasso
import java.io.Serializable

class FavouritesAdapter(private var context: Context, private val movieList: ArrayList<DatabaseMovie>, private val viewModel: HomeViewModel) : RecyclerView.Adapter<FavouritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(LayoutInflater.from(context).inflate(R.layout.favourite_movies_cell, parent, false))
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        if (movieList.size != 0) {
            val movie = movieList[position]
            holder.movieTitleTextView.text = movieList[position].title
            if (movie.poster_path != null) {
                Picasso.get().load("http://image.tmdb.org/t/p/w185" + movie.poster_path).into(holder.moviePoster)
            } else {
                holder.moviePoster.setImageDrawable(context.resources.getDrawable(R.drawable.no_image_available))
            }
            holder.movieVoteAverage.text = " | " + movie.vote_average.toString() + "/10"
            holder.movieGenre.text = viewModel.genresOfMovieSelected(movie.genre_ids).joinToString(", ")

            holder.itemView.setOnClickListener {
                val movieSelected = databaseMovieToMoviesDBMovieMapper(movie)
                val bundle = Bundle()
                bundle.putSerializable("movieSelected", movieSelected as Serializable)
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}
