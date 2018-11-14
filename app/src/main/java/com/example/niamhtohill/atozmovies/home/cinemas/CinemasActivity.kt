package com.example.niamhtohill.atozmovies.home.cinemas

import android.app.Application
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.login.LoginViewModel
import com.example.niamhtohill.atozmovies.login.MyViewModelFactory

class CinemasActivity :AppCompatActivity(), LifecycleOwner {

    private var cinemaTitleBarFragment= CinemaTitleBarFragment()
    private var cinemaShowingsFragment = CinemaShowingsFragment()
    private lateinit var viewModel: CinemasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema)

        val bundle = intent.extras!!
        val cinemaNameBundle= bundle["cinemaName"] as String
        val cinemaId = bundle["cinemaId"] as String

        viewModel = ViewModelProviders.of(this, MyCinemaViewModelFactory(this.application)).get(CinemasViewModel::class.java)
        viewModel.selectedCinemaName.postValue(cinemaNameBundle)
        viewModel.selectedCinemaId.postValue(cinemaId)


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