package com.example.niamhtohill.atozmovies.movieDetail

import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.data.AppDatabase
import com.example.niamhtohill.atozmovies.data.DatabaseFavouriteMovies
import com.example.niamhtohill.atozmovies.data.DatabaseMovie
import com.example.niamhtohill.atozmovies.data.DatabaseWorkerThread
import com.example.niamhtohill.atozmovies.home.topMovies.ActorAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.time.format.DateTimeFormatter
import java.util.*

class MovieDetailActivity : AppCompatActivity(), LifecycleOwner {

    private var db: AppDatabase? = null
    lateinit var viewModel: MovieDetailViewModel

    lateinit var movieSelected: Models.MoviesDBMovie
    private val mUiHandler = Handler()
    private var isSaveFABClicked = false
    private val listOfMovies = ArrayList<DatabaseMovie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        db = AppDatabase.getAppDatabase(this)

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(application)).get(MovieDetailViewModel::class.java)
        viewModel.mDbWorkerThread = DatabaseWorkerThread("dbWorkerThread")
        viewModel.mDbWorkerThread.start()

        val bundle = intent.extras
        movieSelected = bundle.getSerializable("movieSelected") as Models.MoviesDBMovie
        viewModel.fetchCredits(movieSelected.id)
        movie_title_text_view.text = movieSelected.title
        Picasso.get().load("http://image.tmdb.org/t/p/w185" + movieSelected.poster_path).into(movie_poster_image_view)

        viewModel.listOfGenres.observe(this, androidx.lifecycle.Observer {
            movie_genre_text_view.text = viewModel.genresOfMovieSelected(movieSelected.genre_ids).joinToString(", ")
        })

        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)
        movie_date_text_view.text = outputFormatter.format(inputFormatter.parse(movieSelected.release_date))
        movie_rating_text_view.text = movieSelected.vote_average.toString() + "/10"
        overview_text_text_view.text = movieSelected.overview
        actors_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.castMembers.observe(this, androidx.lifecycle.Observer {
            actors_recycler_view.adapter = ActorAdapter(this, viewModel.castMembers.value!!, viewModel)
        })

        actors_recycler_view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                hideFABMenu()
                return false
            }
        })
        add_to_list_fab.setOnClickListener {
            if (!isSaveFABClicked) {
                showFABMenu()
            } else {
                hideFABMenu()
            }
        }

        val taskFetchDB = Runnable {
            if (db?.favouritesDao()?.getTableById(1)!!.isNotEmpty()) {
                for (movie in db?.favouritesDao()?.getTableById(1)!![0].movies) {
                    if (movie.movie_id == movieSelected.id) {
                        mUiHandler.post {
                            add_to_favourite_list_fab.setImageResource(R.drawable.heart_filled)
                        }
                    }
                }
            }
        }
        viewModel.mDbWorkerThread.postTask(taskFetchDB)
    }

    private fun showFABMenu() {
        isSaveFABClicked = true
        add_to_favourite_list_fab.animate().translationY(resources.getDimension(R.dimen.standard_55))
        add_to_favourite_list_fab.setOnClickListener {
            val databaseMovie = DatabaseMovie(
                    movieSelected.id,
                    movieSelected.vote_count,
                    movieSelected.vote_average,
                    movieSelected.video,
                    movieSelected.title,
                    movieSelected.popularity,
                    movieSelected.poster_path,
                    movieSelected.original_language,
                    movieSelected.original_title,
                    movieSelected.genre_ids,
                    movieSelected.backdrop_path,
                    movieSelected.adult,
                    movieSelected.overview,
                    movieSelected.release_date)

            val task = Runnable { db?.moviesDao()?.insertMovie(databaseMovie) }
            viewModel.mDbWorkerThread.postTask(task)
            listOfMovies.removeAll(listOfMovies)
            val taskFetchDB = Runnable {
                for (movie in db?.favouritesDao()?.getTableById(1)!![0].movies) {
                    listOfMovies.add(movie)
                }
                updateUI(databaseMovie)
            }
            viewModel.mDbWorkerThread.postTask(taskFetchDB)
            val taskFavourites = Runnable { db?.favouritesDao()?.insertFavourite(DatabaseFavouriteMovies(1, listOfMovies)) }
            viewModel.mDbWorkerThread.postTask(taskFavourites)
        }
        add_to_watch_list_fab.animate().translationY(resources.getDimension(R.dimen.standard_105))
    }

    private fun updateUI(databaseMovie: DatabaseMovie) {
        if (!listOfMovies.contains(databaseMovie)) {
            listOfMovies.add(databaseMovie)
            mUiHandler.post { add_to_favourite_list_fab.setImageResource(R.drawable.heart_filled) }
            val taskFavourites = Runnable { db?.favouritesDao()?.insertFavourite(DatabaseFavouriteMovies(1, listOfMovies)) }
            viewModel.mDbWorkerThread.postTask(taskFavourites)
        } else {
            listOfMovies.remove(databaseMovie)
            mUiHandler.post { add_to_favourite_list_fab.setImageResource(R.drawable.heart) }
            val taskFavourites = Runnable { db?.favouritesDao()?.insertFavourite(DatabaseFavouriteMovies(1, listOfMovies)) }
            viewModel.mDbWorkerThread.postTask(taskFavourites)
        }
    }

    private fun hideFABMenu() {
        isSaveFABClicked = false
        add_to_favourite_list_fab.animate().translationY(0f)
        add_to_watch_list_fab.animate().translationY(0f)
    }

    override fun onDestroy() {
        viewModel.mDbWorkerThread.quit()
        super.onDestroy()
    }
}
