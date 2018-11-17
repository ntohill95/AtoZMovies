package com.example.niamhtohill.atozmovies.home.cinemas

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.niamhtohill.atozmovies.R

class CinemasActivity : AppCompatActivity(), LifecycleOwner {

    private var cinemaTitleBarFragment = CinemaTitleBarFragment()
    private var cinemaShowingsFragment = CinemaShowingsFragment()
    lateinit var viewModel: CinemasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema)

        val bundle = intent.extras!!
        val cinemaNameBundle = bundle["cinemaName"] as String
        val cinemaId = bundle["cinemaId"] as String
        val postcode = bundle["postcodeSearched"] as String

        viewModel = ViewModelProviders.of(this, MyCinemaViewModelFactory(this.application)).get(CinemasViewModel::class.java)
        viewModel.selectedCinemaName.postValue(cinemaNameBundle)
        viewModel.selectedCinemaId.postValue(cinemaId)
        viewModel.fetchCinemaShowings(cinemaId)
        viewModel.fetchCinemasMoviesAPI(postcode)
        viewModel.listOfMoviesAPiCinemas.observe(this, Observer {
            viewModel.fetchCinema()
        })

        supportFragmentManager
                .beginTransaction()
                .add(R.id.cinema_title_placeholder, cinemaTitleBarFragment)
                .add(R.id.central_content_placeholder, cinemaShowingsFragment)
                .commit()
        supportFragmentManager.executePendingTransactions()
    }
}

class MyCinemaViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CinemasViewModel(application) as T
    }
}