package com.example.niamhtohill.atozmovies.home.tickets

import androidx.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.home.HomeActivity

class TicketsFragment : Fragment() {

    private lateinit var postcodeEditText: EditText
    private lateinit var submitPostcodeButton: Button
    private lateinit var listView: RecyclerView
    private lateinit var noCinemasTextView: TextView
    private lateinit var progressSpinner: ProgressBar
    lateinit var parentBaseActivity: HomeActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_tickets, container, false)
        listView = rootView.findViewById(R.id.cinema_tickets_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        postcodeEditText = rootView.findViewById(R.id.search_cinemas_near)
        submitPostcodeButton = rootView.findViewById(R.id.submit_postcode)
        noCinemasTextView = rootView.findViewById(R.id.no_cinemas_found_textview)
        progressSpinner = rootView.findViewById(R.id.loading_spinner_tickets)

        submitPostcodeButton.setOnClickListener {
            view!!.hideKeyboard()
            parentBaseActivity.viewModel.onPostcodeSearch(postcodeEditText.text.toString().replace(" ", ""))
            parentBaseActivity.viewModel.searchedPostcode.postValue(postcodeEditText.text.toString().replace(" ", ""))
            progressSpinner.visibility = View.VISIBLE
        }
        parentBaseActivity.viewModel.listOfLocalCinemas.observe(this, Observer {
            noCinemasTextView.visibility = View.INVISIBLE
            val ticketsAdapter = TicketsAdapter(context!!, parentBaseActivity.viewModel.listOfLocalCinemas.value!!, parentBaseActivity.viewModel)
            listView.adapter = ticketsAdapter
            progressSpinner.visibility = View.INVISIBLE
            ticketsAdapter.setListener(object : TicketsAdapter.AdapterListener {
                override fun cinemaSelected(name: String) {
                    parentBaseActivity.viewModel.updateSelectedCinema(name)
                }
            })
            if (parentBaseActivity.viewModel.listOfLocalCinemas.value!!.isEmpty()) {
                noCinemasTextView.visibility = View.VISIBLE
                progressSpinner.visibility = View.INVISIBLE
            }
        })

        return rootView
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}