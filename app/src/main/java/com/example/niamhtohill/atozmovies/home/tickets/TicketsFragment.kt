package com.example.niamhtohill.atozmovies.home.tickets

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentTicketsBinding
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.MyViewModelFactory


class TicketsFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var postcodeEditText: EditText
    private lateinit var submitPostcodeButton: Button
    private lateinit var listView: RecyclerView
    private lateinit var noCinemasTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(activity!!, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentTicketsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tickets, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        val rootView = binding.root
        listView = rootView.findViewById(R.id.cinema_tickets_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        postcodeEditText = rootView.findViewById(R.id.search_cinemas_near)
        submitPostcodeButton = rootView.findViewById(R.id.submit_postcode)
        noCinemasTextView = rootView.findViewById(R.id.no_cinemas_found_textview)
        submitPostcodeButton.setOnClickListener {
            view!!.hideKeyboard()
            viewModel.onPostcodeSearch(postcodeEditText.text.toString())
        }
        viewModel.listOfLocalCinemas.observe(this, Observer {
            noCinemasTextView.visibility = View.INVISIBLE
            val ticketsAdapter = TicketsAdapter(context!!, viewModel.listOfLocalCinemas.value!!)
            listView.adapter = ticketsAdapter
            ticketsAdapter.setListener(object : TicketsAdapter.AdapterListener{
                override fun cinemaSelected(name: String) {
                    viewModel.updateSelectedCinema(name)
                }
            })
            if (viewModel.listOfLocalCinemas.value!!.isEmpty()) {
                noCinemasTextView.visibility = View.VISIBLE
            }
        })


        return rootView
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}