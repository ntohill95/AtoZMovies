package com.example.niamhtohill.atozmovies.login

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), LifecycleOwner{
    private lateinit var viewModel: LoginViewModel
    private lateinit var loginProgressBar : ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)
        loginProgressBar = rootView.findViewById(R.id.login_progress_bar)

        viewModel = ViewModelProviders.of(this,  MyViewModelFactory(requireActivity().application)).get(LoginViewModel::class.java)
        val binding : FragmentLoginBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_login)
        binding.viewModel = viewModel
        

        viewModel.showProgressBar.observe(this, Observer { test ->
            if (viewModel.showProgressBar.value == true) {
                loginProgressBar.visibility = View.VISIBLE
            }else {
                loginProgressBar.visibility = View.INVISIBLE
            }
        })
        return rootView
    }
}