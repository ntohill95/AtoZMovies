package com.example.niamhtohill.atozmovies.home

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.home.favourites.FavouritesFragment
import com.example.niamhtohill.atozmovies.home.tickets.TicketsFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

var mFirebaseAnalytics: FirebaseAnalytics? = null

class HomeActivity : AppCompatActivity(), LifecycleOwner, TabBarClickable {

    private var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        setContentView(R.layout.activity_home)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.title_bar_placeholder, TitleBarFragment())
                .add(R.id.central_content_placeholder, FavouritesFragment())
                .add(R.id.tab_bar_placeholder, TabBarFragment())
                .commit()
        supportFragmentManager.executePendingTransactions()
        fbAuth.addAuthStateListener {
            if (fbAuth.currentUser == null) {
                this.finish()
            }
        }
    }

    override fun ticketButtonClicked() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.central_content_placeholder, TicketsFragment())
                .commit()
        supportFragmentManager.executePendingTransactions()
    }
}
