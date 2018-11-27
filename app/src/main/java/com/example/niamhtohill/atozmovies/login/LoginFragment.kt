package com.example.niamhtohill.atozmovies.login

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), LifecycleOwner {
    private lateinit var viewModel: LoginViewModel
    private lateinit var progressSpinner: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(LoginViewModel::class.java)
        val binding: FragmentLoginBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_login)
        binding.viewModel = viewModel
        binding.setVariable(BR.viewModel, viewModel)
        // rootView = binding.root
        progressSpinner = rootView.findViewById(R.id.login_progress_bar)
        viewModel.accountCreated.observe(this, Observer { test ->
            if (viewModel.accountCreated.value == true) {
                showAccountCreatedDialog()
            }
        })
        viewModel.showProgressBar.observe(this, Observer {
            if (viewModel.showProgressBar.value == true) {
                progressSpinner.visibility = View.VISIBLE
                println("***** login spinner true")
            } else {
                progressSpinner.visibility = View.INVISIBLE
                println("***** login spinner false")
            }
        })
        return rootView
    }

    private fun showAccountCreatedDialog() {
        AlertDialog.Builder(this.requireContext())
                .setTitle("Welcome!")
                .setMessage("You have now joined AtoZMovies!")
                .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        dialog.dismiss()
                    }
                }).show()
    }
}