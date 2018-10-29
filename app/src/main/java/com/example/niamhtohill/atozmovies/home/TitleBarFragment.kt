package com.example.niamhtohill.atozmovies.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentTitleBarBinding

class TitleBarFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var logoutButton: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_title_bar, container, false)
        logoutButton = rootView.findViewById(R.id.logout_button)

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentTitleBarBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_title_bar)
        binding.viewModel = viewModel

        return rootView
    }
}