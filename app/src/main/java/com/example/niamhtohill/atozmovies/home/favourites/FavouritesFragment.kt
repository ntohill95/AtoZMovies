package com.example.niamhtohill.atozmovies.home.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.data.*
import com.example.niamhtohill.atozmovies.home.HomeActivity

class FavouritesFragment : Fragment() {

    lateinit var parentBaseActivity: HomeActivity
    private var db: AppDatabase? = null
    private var moviesDao: DaoDatabaseMovie? = null
    private var favouritesDao: DaoDatabaseFavouriteMovies? = null
    private var favouritesMovieList = ArrayList<DatabaseMovie>()
    private lateinit var listView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_favourites, container, false)
        db = AppDatabase.getAppDatabase(context!!)
        moviesDao = db?.moviesDao()
        favouritesDao = db?.favouritesDao()
        listView = rootView.findViewById(R.id.favourites_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        listView.adapter = FavouritesAdapter(context!!, favouritesMovieList, parentBaseActivity.viewModel)
        parentBaseActivity.viewModel.fetchFavourites(taskFetchDB)

        parentBaseActivity.viewModel.listOfGenres.observe(this, Observer {
            listView.adapter!!.notifyDataSetChanged()
        })
        return rootView
    }

    override fun onResume() {
        super.onResume()
        println("********* on resume reload list")
        parentBaseActivity.viewModel.fetchFavourites(taskFetchDB)

    }

    private val taskFetchDB = Runnable {
        if (db?.favouritesDao()?.getTableById(1)!!.isNotEmpty()) {
            for (movie in db?.favouritesDao()?.getTableById(1)!![0].movies) {
                if (!favouritesMovieList.contains(movie)) {
                    favouritesMovieList.add(movie)
                    activity!!.runOnUiThread {
                        listView.adapter!!.notifyDataSetChanged()
                    }
                }
            }
            val iterator = favouritesMovieList.iterator()
            while (iterator.hasNext()) {
                val movie = iterator.next()
                if (!(db?.favouritesDao()?.getTableById(1)!![0].movies.contains(movie))) {
                    iterator.remove()
                    activity!!.runOnUiThread {
                        listView.adapter!!.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}