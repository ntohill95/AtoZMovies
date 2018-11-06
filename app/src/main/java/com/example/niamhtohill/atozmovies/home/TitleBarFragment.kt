package com.example.niamhtohill.atozmovies.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentTitleBarBinding

class TitleBarFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var logoutButton: ImageButton
    lateinit var titleBarTextView : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentTitleBarBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_title_bar, container, false)
        binding.setVariable(BR.viewModel, viewModel)

        val rootView = binding.root

        logoutButton = rootView.findViewById(R.id.logout_button)
        titleBarTextView = rootView.findViewById(R.id.title_text_view)
        return rootView
    }
}