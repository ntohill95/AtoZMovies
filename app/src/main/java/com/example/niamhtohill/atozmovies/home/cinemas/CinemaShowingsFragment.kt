package com.example.niamhtohill.atozmovies.home.cinemas

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentCinemaShowingsBinding

class CinemaShowingsFragment : Fragment() {

    private lateinit var viewModel: CinemasViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, MyCinemaViewModelFactory(requireActivity().application)).get(CinemasViewModel::class.java)
        val binding: FragmentCinemaShowingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cinema_showings, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        val rootView = binding.root

        return rootView
    }
}