package com.example.niamhtohill.atozmovies.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class CheckInternet(private var context: Context) : AsyncTask<Void, Void, Boolean>() {

    override fun doInBackground(vararg p0: Void?): Boolean {
        return hasActiveInternetConnection(context)
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null
}

fun hasActiveInternetConnection(context: Context): Boolean {
    if (isNetworkAvailable(context)) {
        try {
            val urlc = URL("http://www.google.com").openConnection() as HttpURLConnection
            urlc.setRequestProperty("User-Agent", "Test")
            urlc.setRequestProperty("Connection", "close")
            urlc.setConnectTimeout(1500)
            urlc.connect()
            return urlc.getResponseCode() == 200
        } catch (e: IOException) {
            Log.e("Util - internet connection", "Error checking internet connection", e)
        }
    } else {
        Log.d("Util - internet connection", "No network available!")
    }
    return false
}