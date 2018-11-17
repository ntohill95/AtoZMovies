package com.example.niamhtohill.atozmovies.home.cinemas

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentCinemaTitleBarBinding

class CinemaTitleBarFragment : Fragment() {

    private lateinit var viewModel: CinemasViewModel
    private lateinit var cinemaName: TextView
    private lateinit var cinemaAddress: TextView
    private lateinit var sundayButton: Button
    private lateinit var mondayButton: Button
    private lateinit var tuesdayButton: Button
    private lateinit var wednesdayButton: Button
    private lateinit var thursdayButton: Button
    private lateinit var fridayButton: Button
    private lateinit var saturdayButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(activity!!, MyCinemaViewModelFactory(requireActivity().application)).get(CinemasViewModel::class.java)
        val binding: FragmentCinemaTitleBarBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cinema_title_bar, container, false)
        binding.setVariable(BR.viewModel, viewModel)

        val rootView = binding.root
        cinemaName = rootView.findViewById(R.id.cinema_title_text_view)
        cinemaAddress = rootView.findViewById(R.id.cinema_address_text_view)
        sundayButton = rootView.findViewById(R.id.sunday_tab_button)
        mondayButton = rootView.findViewById(R.id.monday_tab_button)
        tuesdayButton = rootView.findViewById(R.id.tuesday_tab_button)
        wednesdayButton = rootView.findViewById(R.id.wednesday_tab_button)
        thursdayButton = rootView.findViewById(R.id.thursday_tab_button)
        fridayButton = rootView.findViewById(R.id.friday_tab_button)
        saturdayButton = rootView.findViewById(R.id.saturday_tab_button)

        cinemaName.text = viewModel.selectedCinemaName.value
        cinemaName.setTypeface(null, Typeface.BOLD)
        cinemaName.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        viewModel.selectedCinemaName.observe(this, Observer {
            cinemaName.text = viewModel.selectedCinemaName.value
        })

        viewModel.selectedCinema.observe(this, Observer {
            cinemaAddress.text = viewModel.selectedCinema.value!!.address

        })
        return rootView
    }
}
