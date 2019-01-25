package com.example.niamhtohill.atozmovies.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.home.HomeActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

var mFirebaseAnalytics: FirebaseAnalytics? = null
var mFirebaseAuth: FirebaseAuth? = null

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mFirebaseAuth = FirebaseAuth.getInstance()

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_login_placeholder, LoginFragment())
                .commit()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}