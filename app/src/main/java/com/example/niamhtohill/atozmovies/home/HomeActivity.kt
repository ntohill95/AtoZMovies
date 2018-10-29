package com.example.niamhtohill.atozmovies.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.niamhtohill.atozmovies.R
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

var mFirebaseAnalytics: FirebaseAnalytics? = null

class HomeActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.title_bar_placeholder, TitleBarFragment())
                .commit()
        supportFragmentManager.executePendingTransactions()

        fbAuth.addAuthStateListener {
            if (fbAuth.currentUser == null) {
                this.finish()
            }
        }
    }
}
