package com.example.niamhtohill.atozmovies.home.cinemas

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class CinemasViewModel(private var application: Application) : ViewModel() {

    var selectedCinemaName = MutableLiveData<String>()
    var selectedCinemaId = MutableLiveData<String>()

}