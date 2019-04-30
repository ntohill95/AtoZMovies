package com.example.niamhtohill.atozmovies.movieDetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.niamhtohill.atozmovies.api.API_KEY
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.api.MovieDatabaseService
import com.example.niamhtohill.atozmovies.data.DatabaseWorkerThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(private val application: Application) : ViewModel() {

    lateinit var mDbWorkerThread: DatabaseWorkerThread
    var castMembers = MutableLiveData<List<Models.MovieCastMember>>()
    var listOfGenres = MutableLiveData<List<Models.MovieGenre>>()
    var genresOfMovie = ArrayList<String>()

    private var disposable: Disposable? = null
    private val moviesDatabaseService by lazy {
        MovieDatabaseService.create()
    }

    init {
        if (listOfGenres.value == null) {
            fetchGenreNames()
        }
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

    private fun fetchGenreNames() {
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
}

class MyViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(application) as T
    }
}