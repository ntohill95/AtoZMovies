package com.example.niamhtohill.atozmovies.login

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.niamhtohill.atozmovies.R
import com.google.firebase.analytics.FirebaseAnalytics

private var mFirebaseAnalytics: FirebaseAnalytics? = null

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_login_placeholder, LoginFragment())
                .commit()
    }

    fun onLoginClicked(view:View){

    }


}