package com.example.niamhtohill.atozmovies.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.niamhtohill.atozmovies.R
import com.google.firebase.analytics.FirebaseAnalytics


private var mFirebaseAnalytics: FirebaseAnalytics? = null

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }
}
