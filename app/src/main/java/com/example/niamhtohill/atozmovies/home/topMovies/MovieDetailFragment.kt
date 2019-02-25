package com.example.niamhtohill.atozmovies.home.topMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.home.HomeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import java.time.format.DateTimeFormatter
import java.util.*

class MovieDetailFragment : Fragment() {

    private lateinit var movieTitle: TextView
    private lateinit var moviePoster: ImageView
    private lateinit var movieGenre: TextView
    private lateinit var movieReleaseDate: TextView
    private lateinit var movieRating: TextView
    private lateinit var movieActorsRecyclerView: RecyclerView
    private lateinit var movieOverview: TextView
    private lateinit var saveToFab: FloatingActionButton
    private lateinit var saveToFavourites: FloatingActionButton
    private lateinit var saveToWatch: FloatingActionButton
    private var isSaveFABClicked = false
    lateinit var parentBaseActivity: HomeActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        val bundle = arguments!!
        val movieSelected = bundle["movieSelected"] as Models.MoviesDBMovie
        parentBaseActivity.viewModel.fetchCredits(movieSelected.id)
        movieTitle = rootView.findViewById(R.id.movie_title_text_view)
        movieTitle.text = movieSelected.title
        moviePoster = rootView.findViewById(R.id.movie_poster_image_view)
        Picasso.get().load("http://image.tmdb.org/t/p/w185" + movieSelected.poster_path).into(moviePoster)
        movieGenre = rootView.findViewById(R.id.movie_genre_text_view)
        movieGenre.text = parentBaseActivity.viewModel.genresOfMovieSelected(movieSelected.genre_ids).joinToString(", ")
        movieReleaseDate = rootView.findViewById(R.id.movie_date_text_view)
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
        movieReleaseDate.text = outputFormatter.format(inputFormatter.parse(movieSelected.release_date))
        movieRating = rootView.findViewById(R.id.movie_rating_text_view)
        movieRating.text = movieSelected.vote_average.toString() + "/10"
        movieOverview = rootView.findViewById(R.id.overview_text_text_view)
        movieOverview.text = movieSelected.overview
        movieActorsRecyclerView = rootView.findViewById(R.id.actors_recycler_view)
        movieActorsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        parentBaseActivity.viewModel.castMembers.observe(this, Observer {
            movieActorsRecyclerView.adapter = ActorAdapter(context!!, parentBaseActivity.viewModel.castMembers.value!!, parentBaseActivity.viewModel)
        })

        saveToFab = rootView.findViewById(R.id.add_to_list_fab)
        saveToFavourites = rootView.findViewById(R.id.add_to_favourite_list_fab)
        saveToWatch = rootView.findViewById(R.id.add_to_watch_list_fab)

        saveToFab.setOnClickListener {
            if (!isSaveFABClicked) {
                showFABMenu()
            } else {
                hideFABMenu()
            }
        }

        return rootView
    }

    private fun showFABMenu() {
        isSaveFABClicked = true
        saveToFavourites.animate().translationY(resources.getDimension(R.dimen.standard_55))
        saveToWatch.animate().translationY(resources.getDimension(R.dimen.standard_105))
    }

    private fun hideFABMenu() {
        isSaveFABClicked = false
        saveToFavourites.animate().translationY(0f)
        saveToWatch.animate().translationY(0f)
    }
}