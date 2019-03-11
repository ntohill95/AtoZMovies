package com.example.niamhtohill.atozmovies.home.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.data.AppDatabase
import com.example.niamhtohill.atozmovies.data.DaoDatabaseFavouriteMovies
import com.example.niamhtohill.atozmovies.data.DaoDatabaseMovie
import com.example.niamhtohill.atozmovies.data.DatabaseWorkerThread
import com.example.niamhtohill.atozmovies.home.HomeActivity

class FavouritesFragment : Fragment() {

    lateinit var parentBaseActivity: HomeActivity
    private var db: AppDatabase? = null
    private var moviesDao: DaoDatabaseMovie? = null
    private var favouritesDao: DaoDatabaseFavouriteMovies? = null
    private lateinit var mDbWorkerThread: DatabaseWorkerThread


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_favourites, container, false)
        db = AppDatabase.getAppDatabase(context!!)
        moviesDao = db?.moviesDao()
        favouritesDao = db?.favouritesDao()
        mDbWorkerThread = DatabaseWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        val listView: RecyclerView = rootView.findViewById(R.id.favourites_list_view)
//        listView.layoutManager = LinearLayoutManager(this.context)
//        listView.adapter = FavouritesAdapter(context!!, generatedFakeData())

        val taskFetchDB = Runnable {
            if (db?.favouritesDao()?.getTableById(1)!!.isNotEmpty()) {
                for (movie in db?.favouritesDao()?.getTableById(1)!![0].movies) {
                    println("********** size = "+ db?.favouritesDao()?.getTableById(1)!![0].movies.size)
                }
            }
        }
        mDbWorkerThread.postTask(taskFetchDB)

        return rootView
    }
}