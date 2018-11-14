package com.example.niamhtohill.atozmovies.home.cinemas

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentCinemaTitleBarBinding

class CinemaTitleBarFragment : Fragment() {

    private lateinit var viewModel: CinemasViewModel
    private lateinit var cinemaName: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(activity!!, MyCinemaViewModelFactory(requireActivity().application)).get(CinemasViewModel::class.java)
        val binding: FragmentCinemaTitleBarBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cinema_title_bar, container, false)
        binding.setVariable(BR.viewModel, viewModel)

        val rootView = binding.root
        cinemaName = rootView.findViewById(R.id.cinema_title_text_view)
        cinemaName.text = viewModel.selectedCinemaName.value
        viewModel.selectedCinemaName.observe(this, Observer {
            cinemaName.text = viewModel.selectedCinemaName.value
        })

        return rootView
    }
}
