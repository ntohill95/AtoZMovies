package com.example.niamhtohill.atozmovies.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.content.Intent
import android.view.View
import com.example.niamhtohill.atozmovies.api.CinemaPostcodeService
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.api.MovieDatabaseService
import com.example.niamhtohill.atozmovies.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val application: Application) : ViewModel() {

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

    fun updateSelectedCinema(cinemaName: String) {
        selectedCinema.postValue(cinemaName)
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