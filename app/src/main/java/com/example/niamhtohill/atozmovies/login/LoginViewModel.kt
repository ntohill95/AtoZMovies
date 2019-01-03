package com.example.niamhtohill.atozmovies.login

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.niamhtohill.atozmovies.home.HomeActivity
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider

lateinit var credentials: AuthCredential

class LoginViewModel(private val application: Application) : ViewModel() {

    val email = MutableLiveData<String>().apply { postValue("niamhtohill95@yahoo.co.uk") }
    val password = MutableLiveData<String>().apply { postValue("testing1!") }
    val showProgressBar = MutableLiveData<Boolean>().apply { postValue(false) }
    val accountCreated = MutableLiveData<Boolean>().apply { postValue(false) }

    var progressVisibility = View.INVISIBLE

    fun onLoginClicked(view: View) {
        view.hideKeyboard()
        showProgressBar.postValue(true)
        progressVisibility = View.VISIBLE
        if (email.value != "" && password.value != "") {
            val email = email.value!!
            val password = password.value!!

            credentials = EmailAuthProvider.getCredential(email, password)
            loginToFirebase(email, password)
        } else {
            Toast.makeText(application, "Please enter valid credentials", Toast.LENGTH_SHORT).show()
            progressVisibility = View.INVISIBLE
            showProgressBar.postValue(false)
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun onSignUpClicked(view: View) {
        if (email.value != "" && password.value != "") {
            val email = email.value!!
            val password = password.value!!
            createFirebaseAccount(email, password)
            showProgressBar.postValue(true)
            progressVisibility = View.VISIBLE
        } else {
            Toast.makeText(application, "Please enter valid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginToFirebase(email: String, password: String) {
        showProgressBar.postValue(true)
        progressVisibility = View.VISIBLE

        println("*************** loggin in to firebase")
        mFirebaseAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                println("*************** logging in successful")
                val currentUser = mFirebaseAuth!!.currentUser
                // TODO store current user in database
                showProgressBar.postValue(false)
                progressVisibility = View.INVISIBLE
                application.startActivity(Intent(application, HomeActivity::class.java))
            } else {
                println("*************** logging in nope")
                showProgressBar.postValue(false)
                progressVisibility = View.INVISIBLE
                Toast.makeText(application, "Unsuccessful login", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createFirebaseAccount(email: String, password: String) {
        mFirebaseAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        accountCreated.postValue(true)
                        Toast.makeText(application, "Successful create account", Toast.LENGTH_SHORT).show()
                    } else {
                        accountCreated.postValue(false)
                        Toast.makeText(application, "Unsuccessful create account", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}

class MyViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(application) as T
    }
}
