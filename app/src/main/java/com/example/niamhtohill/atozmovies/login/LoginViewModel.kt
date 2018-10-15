package com.example.niamhtohill.atozmovies.login

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.view.View
import android.widget.Toast


class LoginViewModel(private val application: Application )  : ViewModel() {

    val email = MutableLiveData<String>().apply {  postValue("niamhtohill95@yahoo.co.uk")}
    val password = MutableLiveData<String>().apply { postValue("testing1!")}

    fun onLoginClicked(view: View) {
        Toast.makeText(application, "Login clicked", Toast.LENGTH_SHORT).show()

        if (email.value != "" && password.value != "") {
            val email = email.value!!
            val password = password.value!!
            loginToFirebase(email, password)
        } else {
             Toast.makeText(application, "Please enter valid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    fun onSignUpClicked(view: View) {
        if (email.value!= "" && password.value != "") {
            val email  = email.value!!
            val password  = password.value!!
            createFirebaseAccount(email, password)
        } else {
            Toast.makeText(application, "Please enter valid credentials", Toast.LENGTH_SHORT).show()
        }
   }

    private fun loginToFirebase(email: String, password: String) {
        mFirebaseAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(application, "Successful login", Toast.LENGTH_SHORT).show()
                        val currentUser = mFirebaseAuth!!.currentUser

                    } else {
                        Toast.makeText(application, "Unsuccessful login", Toast.LENGTH_SHORT).show()

                    }
                }
    }

    private fun createFirebaseAccount(email: String, password: String) {
        mFirebaseAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(application, "Successful create account", Toast.LENGTH_SHORT).show()
                    } else {
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

