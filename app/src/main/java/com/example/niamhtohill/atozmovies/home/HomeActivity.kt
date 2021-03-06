package com.example.niamhtohill.atozmovies.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.data.AppDatabase
import com.example.niamhtohill.atozmovies.data.DaoDatabaseMovie
import com.example.niamhtohill.atozmovies.data.DatabaseWorkerThread
import com.example.niamhtohill.atozmovies.home.favourites.FavouritesFragment
import com.example.niamhtohill.atozmovies.home.recommendation.RecommendationFragment
import com.example.niamhtohill.atozmovies.home.search.SearchFragment
import com.example.niamhtohill.atozmovies.home.tickets.TicketsFragment
import com.example.niamhtohill.atozmovies.home.topMovies.PopularMoviesFragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

var mFirebaseAnalytics: FirebaseAnalytics? = null

class HomeActivity : AppCompatActivity(), LifecycleOwner, TabBarClickable {

    private var fbAuth = FirebaseAuth.getInstance()
    lateinit var viewModel: HomeViewModel
    private var titleBarFragment = TitleBarFragment()
    private var tabBarFragment = TabBarFragment()
    private var favouritesFragment = FavouritesFragment()
    private var topMoviesFragment = PopularMoviesFragment()
    private var ticketsFragment = TicketsFragment()
    private var recommendationFragment = RecommendationFragment()
    private var searchFragment = SearchFragment()

    private var db: AppDatabase? = null
    private var movieDao: DaoDatabaseMovie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = AppDatabase.getAppDatabase(this)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(application)).get(HomeViewModel::class.java)

        viewModel.mDbWorkerThread = DatabaseWorkerThread("dbWorkerThread")
        viewModel.mDbWorkerThread.start()

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
        titleBarFragment.titleBarTextView.text = resources.getString(R.string.popular)
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

    override fun onDestroy() {
        viewModel.mDbWorkerThread.quit()
        super.onDestroy()
    }
}
