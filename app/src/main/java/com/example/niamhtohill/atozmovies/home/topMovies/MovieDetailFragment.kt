package com.example.niamhtohill.atozmovies.home.topMovies

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.databinding.FragmentMovieDetailBinding
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.MyViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentMovieDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        val rootView = binding.root
        val bundle = arguments!!
        val movieSelected = bundle["movieSelected"] as Models.MoviesDBMovie
        movieTitle = rootView.findViewById(R.id.movie_title_text_view)
        movieTitle.text = movieSelected.title
        moviePoster = rootView.findViewById(R.id.movie_poster_image_view)
        //TODO 2 ensure this is correctly converted to Kotlin code
        //.placeholder(R.drawable.TODO)
        Picasso.get().load("http://image.tmdb.org/t/p/w185" + movieSelected.poster_path).into(moviePoster)
        movieGenre = rootView.findViewById(R.id.movie_genre_text_view)
        movieGenre.text = movieSelected.genre_ids.toString()
        movieReleaseDate = rootView.findViewById(R.id.movie_date_text_view)
        movieReleaseDate.text = movieSelected.release_date
        movieRating = rootView.findViewById(R.id.movie_rating_text_view)
        movieRating.text = movieSelected.vote_average.toString()
        movieOverview = rootView.findViewById(R.id.overview_text_text_view)
        movieOverview.text = movieSelected.overview
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