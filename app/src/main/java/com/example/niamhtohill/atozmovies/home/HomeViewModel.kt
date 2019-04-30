package com.example.niamhtohill.atozmovies.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.content.Intent
import android.view.View
import com.example.niamhtohill.atozmovies.api.API_KEY
import com.example.niamhtohill.atozmovies.api.CinemaPostcodeService
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.api.MovieDatabaseService
import com.example.niamhtohill.atozmovies.data.DatabaseWorkerThread
import com.example.niamhtohill.atozmovies.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val application: Application) : ViewModel() {

    lateinit var mDbWorkerThread: DatabaseWorkerThread

    fun onLogoutClicked(view: View) {
        logoutOfFirebase()
    }

    private fun logoutOfFirebase() {
        FirebaseAuth.getInstance().signOut()
        application.startActivity(Intent(application, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private var disposable: Disposable? = null
    private val cinemaPostcodeService by lazy {
        CinemaPostcodeService.create()
    }
    private val moviesDatabaseService by lazy {
        MovieDatabaseService.create()
    }

    var listOfLocalCinemas = MutableLiveData<List<Models.CineListCinema>>()

    var selectedCinema = MutableLiveData<String>()

    var searchedPostcode = MutableLiveData<String>()

    var listOPopularMovies = MutableLiveData<List<Models.MoviesDBMovie>>()
    var listOfGenres = MutableLiveData<List<Models.MovieGenre>>()
    var castMembers = MutableLiveData<List<Models.MovieCastMember>>()
    var genresOfMovie = ArrayList<String>()
    var searchResults = MutableLiveData<List<Models.MoviesDBMovie>>()

    init {
        if (listOfGenres.value == null) {
            fetchGenreNames()
        }
    }

    fun fetchFavourites(runnable: Runnable) {
        mDbWorkerThread.postTask(runnable)
    }

    fun onPostcodeSearch(postcode: String) {
        disposable = cinemaPostcodeService
                .fetchCinemasPostcode(postcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> listOfLocalCinemas.postValue(result.cinemas) },
                        { error -> println(error) }
                )
    }

    fun onSearchFilm(searchString: String) {
        disposable = moviesDatabaseService
                .searchForFilm(searchString, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> searchResults.postValue(result.results) },
                        { error -> println(error) })
    }

    fun updateSelectedCinema(cinemaName: String) {
        selectedCinema.postValue(cinemaName)
    }

    fun fetchGenreNames() {
        disposable = moviesDatabaseService
                .fetchGenres(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            listOfGenres.postValue(result.genres)
                        },
                        { error ->
                            println(error)
                        })
    }

    fun fetchCredits(movieId: Int) {
        disposable = moviesDatabaseService
                .fetchCredits(movieId, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> castMembers.postValue(result.cast) },
                        { error -> println(error) })
    }

    fun genresOfMovieSelected(genreIds: List<Int>): ArrayList<String> {
        if (genresOfMovie.isNotEmpty()) {
            genresOfMovie.removeAll(genresOfMovie)
        }
        for (id in genreIds) {
            if (listOfGenres.value != null) {
                val genre = listOfGenres.value!!.stream().filter { genre -> genre.id == id }.findFirst().orElse(Models.MovieGenre(0, ""))
                genresOfMovie.add(genre.name)
            }
        }
        return genresOfMovie
    }

    fun onPopularMoviesSearch(apiKey: String) {
        disposable = moviesDatabaseService
                .fetchPopularMovies(apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> listOPopularMovies.postValue(result.results) },
                        { error -> println(error) }
                )
    }
}

class MyViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(application) as T
    }
}