package com.example.niamhtohill.atozmovies.home

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.view.View
import com.example.niamhtohill.atozmovies.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel(private val application: Application) : ViewModel() {

    fun onLogoutClicked(view: View) {
        logoutOfFirebase()
    }

    private fun logoutOfFirebase() {
        println("*********LOGOUT CLICKED")
        FirebaseAuth.getInstance().signOut()
        application.startActivity(Intent(application, LoginActivity::class.java))
    }
}

class MyViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(application) as T
    }
}