package com.example.niamhtohill.atozmovies.home.cinemas

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.niamhtohill.atozmovies.api.CinemaShowingsService
import com.example.niamhtohill.atozmovies.api.Models
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CinemasViewModel(private var application: Application) : ViewModel() {

    var selectedCinemaName = MutableLiveData<String>()
    var selectedCinemaId = MutableLiveData<String>()
    var selectedCinema = MutableLiveData<Models.MoviesApiCinema>()
    var postcode: String = ""
    private var disposable: Disposable? = null

    var listOfMoviesAPiCinemas = MutableLiveData<List<Models.MoviesApiCinema>>()
    var listOfShowings = MutableLiveData<List<Models.MoviesApiShowings>>()
    var showingsFetched = MutableLiveData<Boolean>().apply { postValue(false) }

    private val cinemaShowingService by lazy {
        CinemaShowingsService.create()
    }

    fun fetchCinemaShowings(cinemaId: String) {
        disposable = cinemaShowingService
                .fetchCinemaShowings(cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showingsFetched.postValue(false) }
                .subscribe(
                        { result ->
                            listOfShowings.postValue(result)
                            showingsFetched.postValue(true)
                        },
                        { error -> println(error) }
                )
    }
    fun fetchCinemasMoviesAPI(postcode: String) {
        disposable = cinemaShowingService
                .fetchMoviesApiCinemasPostcode(postcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            listOfMoviesAPiCinemas.postValue(result)
                        },
                        { error -> println(error) }
                )
    }
    fun fetchCinema() {
        for (cinema in listOfMoviesAPiCinemas.value!!) {
            if (cinema.venue_id == selectedCinemaId.value) {
                selectedCinema.postValue(cinema)
            }
        }
    }

    fun fetchCinemaShowingsForDay(day: String) {
        disposable = cinemaShowingService
                .fetchShowingsForDay(selectedCinemaId.value!!, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showingsFetched.postValue(false) }
                .subscribe(
                        { result ->
                            listOfShowings.postValue(result)
                            showingsFetched.postValue(true)
                        },
                        { error -> println(error) }
                )
    }
}