package com.example.niamhtohill.atozmovies.home.favourites

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.data.DatabaseMovie
import com.example.niamhtohill.atozmovies.data.databaseMovieToMoviesDBMovieMapper
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.topMovies.MovieDetailFragment
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
                val movieDetailFragment = MovieDetailFragment()
                val bundle = Bundle()
                bundle.putSerializable("movieSelected", movieSelected as Serializable)
                movieDetailFragment.arguments = bundle
                // TODO convert MovieDetail to activity instead of fragment - handle onBack better
                val activity = context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.central_content_placeholder, movieDetailFragment).commit()
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}
