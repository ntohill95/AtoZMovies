package com.example.niamhtohill.atozmovies.home

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.home.favourites.FavouritesFragment
import com.example.niamhtohill.atozmovies.home.recommendation.RecommendationFragment
import com.example.niamhtohill.atozmovies.home.tickets.TicketsFragment
import com.example.niamhtohill.atozmovies.home.topMovies.TopMoviesFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

var mFirebaseAnalytics: FirebaseAnalytics? = null

class HomeActivity : AppCompatActivity(), LifecycleOwner, TabBarClickable {

    private var fbAuth = FirebaseAuth.getInstance()
    private var titleBarFragment = TitleBarFragment()
    private var tabBarFragment = TabBarFragment()
    private var favouritesFragment = FavouritesFragment()
    private var topMoviesFragment = TopMoviesFragment()
    private var ticketsFragment = TicketsFragment()
    private var recommendationFragment = RecommendationFragment()
    private var searchFragment = SearchFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        setContentView(R.layout.activity_home)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.title_bar_placeholder, titleBarFragment)
                .add(R.id.central_content_placeholder, favouritesFragment)
                .add(R.id.tab_bar_placeholder, tabBarFragment)
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
                .replace(R.id.central_content_placeholder, ticketsFragment)
                .commit()
        supportFragmentManager.executePendingTransactions()
        titleBarFragment.titleBarTextView.text = resources.getString(R.string.tickets)

    }

    override fun favouriteButtonClicked() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.central_content_placeholder, favouritesFragment)
                .commit()
        supportFragmentManager.executePendingTransactions()
        titleBarFragment.titleBarTextView.text = resources.getString(R.string.favourites)
    }

    override fun topMovieButtonClicked() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.central_content_placeholder, topMoviesFragment)
                .commit()
        supportFragmentManager.executePendingTransactions()
        titleBarFragment.titleBarTextView.text = resources.getString(R.string.top_movies)

    }

    override fun recommendationButtonClicked() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.central_content_placeholder, recommendationFragment)
                .commit()
        supportFragmentManager.executePendingTransactions()
        titleBarFragment.titleBarTextView.text = resources.getString(R.string.recommendations)
    }

    override fun searchButtonClicked() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.central_content_placeholder, searchFragment)
                .commit()
        supportFragmentManager.executePendingTransactions()
        titleBarFragment.titleBarTextView.text = resources.getString(R.string.search)
    }
}
