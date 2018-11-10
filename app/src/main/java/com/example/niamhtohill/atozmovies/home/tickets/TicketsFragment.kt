package com.example.niamhtohill.atozmovies.home.tickets

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.CinemaPostcodeService
import com.example.niamhtohill.atozmovies.databinding.FragmentTicketsBinding
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.MyViewModelFactory
import com.example.niamhtohill.atozmovies.utils.CheckInternet
import com.example.niamhtohill.atozmovies.utils.hasActiveInternetConnection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TicketsFragment : Fragment() {


    private var fakeList = ArrayList<String>()
    private lateinit var viewModel: HomeViewModel
    private lateinit var postcodeEditText: EditText
    private lateinit var submitPostcodeButton: Button

    private var disposable: Disposable? = null
    private val cinemaPostcodeService by lazy {
        CinemaPostcodeService.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentTicketsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tickets, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        val rootView = binding.root
        val listView: RecyclerView = rootView.findViewById(R.id.cinema_tickets_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        listView.adapter = TicketsAdapter(context!!, generatedFakeData())
        postcodeEditText = rootView.findViewById(R.id.search_cinemas_near)
        submitPostcodeButton = rootView.findViewById(R.id.submit_postcode)
        submitPostcodeButton.setOnClickListener {
            cinemaPostcodeSearch(postcodeEditText.text.toString())
        }
        return rootView
    }

    private fun cinemaPostcodeSearch(postcode: String) {
        disposable = cinemaPostcodeService
                .fetchCinemasPostcode(postcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> println("********* Postcode Result = " + result.postcode) },
                        { error -> println("*******error = " + error) }
                )
    }

    private fun generatedFakeData(): ArrayList<String> {
        fakeList.clear()
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        fakeList.add("MovieHouse - Dublin Road")
        return fakeList
    }
}