package com.example.niamhtohill.atozmovies.home.tickets

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import androidx.databinding.DataBindingUtil
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
    private lateinit var progressSpinner: ProgressBar

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
        progressSpinner = rootView.findViewById(R.id.loading_spinner_tickets)

        submitPostcodeButton.setOnClickListener {
            view!!.hideKeyboard()
            viewModel.onPostcodeSearch(postcodeEditText.text.toString().replace(" ", ""))
            viewModel.searchedPostcode.postValue(postcodeEditText.text.toString().replace(" ", ""))
            progressSpinner.visibility = View.VISIBLE
        }
        viewModel.listOfLocalCinemas.observe(this, Observer {
            noCinemasTextView.visibility = View.INVISIBLE
            val ticketsAdapter = TicketsAdapter(context!!, viewModel.listOfLocalCinemas.value!!, viewModel)
            listView.adapter = ticketsAdapter
            progressSpinner.visibility = View.INVISIBLE
            ticketsAdapter.setListener(object : TicketsAdapter.AdapterListener {
                override fun cinemaSelected(name: String) {
                    viewModel.updateSelectedCinema(name)
                }
            })
            if (viewModel.listOfLocalCinemas.value!!.isEmpty()) {
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