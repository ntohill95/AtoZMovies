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
import android.widget.LinearLayout
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
    private lateinit var weekdayLayout: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(activity!!, MyCinemaViewModelFactory(requireActivity().application)).get(CinemasViewModel::class.java)
        val binding: FragmentCinemaTitleBarBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cinema_title_bar, container, false)
        binding.setVariable(BR.viewModel, viewModel)

        val rootView = binding.root
        cinemaName = rootView.findViewById(R.id.cinema_title_text_view)
        cinemaAddress = rootView.findViewById(R.id.cinema_address_text_view)
        weekdayLayout = rootView.findViewById(R.id.weekday_buttons_layout)
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

        sundayButton.setOnClickListener {
            viewModel.fetchCinemaShowingsForDay("sunday")
            for (i in 0..6) {
                val view = weekdayLayout.getChildAt(i)
                view.background = resources.getDrawable(R.color.colorPrimaryLight)
            }
            sundayButton.background = resources.getDrawable(R.color.colorTextIcons)
        }
        mondayButton.setOnClickListener {
            viewModel.fetchCinemaShowingsForDay("monday")
            for (i in 0..6) {
                val view = weekdayLayout.getChildAt(i)
                view.background = resources.getDrawable(R.color.colorPrimaryLight)
            }
            mondayButton.background = resources.getDrawable(R.color.colorTextIcons)
        }
        tuesdayButton.setOnClickListener {
            viewModel.fetchCinemaShowingsForDay("tuesday")
            viewModel.fetchCinemaShowingsForDay("monday")
            for (i in 0..6) {
                val view = weekdayLayout.getChildAt(i)
                view.background = resources.getDrawable(R.color.colorPrimaryLight)
            }
            tuesdayButton.background = resources.getDrawable(R.color.colorTextIcons)
        }
        wednesdayButton.setOnClickListener {
            viewModel.fetchCinemaShowingsForDay("wednesday")
            viewModel.fetchCinemaShowingsForDay("monday")
            for (i in 0..6) {
                val view = weekdayLayout.getChildAt(i)
                view.background = resources.getDrawable(R.color.colorPrimaryLight)
            }
            wednesdayButton.background = resources.getDrawable(R.color.colorTextIcons)
        }
        thursdayButton.setOnClickListener {
            viewModel.fetchCinemaShowingsForDay("thursday")
            viewModel.fetchCinemaShowingsForDay("monday")
            for (i in 0..6) {
                val view = weekdayLayout.getChildAt(i)
                view.background = resources.getDrawable(R.color.colorPrimaryLight)
            }
            thursdayButton.background = resources.getDrawable(R.color.colorTextIcons)
        }
        fridayButton.setOnClickListener {
            viewModel.fetchCinemaShowingsForDay("friday")
            viewModel.fetchCinemaShowingsForDay("monday")
            for (i in 0..6) {
                val view = weekdayLayout.getChildAt(i)
                view.background = resources.getDrawable(R.color.colorPrimaryLight)
            }
            fridayButton.background = resources.getDrawable(R.color.colorTextIcons)
        }
        saturdayButton.setOnClickListener {
            viewModel.fetchCinemaShowingsForDay("saturday")
            viewModel.fetchCinemaShowingsForDay("monday")
            for (i in 0..6) {
                val view = weekdayLayout.getChildAt(i)
                view.background = resources.getDrawable(R.color.colorPrimaryLight)
            }
            saturdayButton.background = resources.getDrawable(R.color.colorTextIcons)
        }
        return rootView
    }
}
