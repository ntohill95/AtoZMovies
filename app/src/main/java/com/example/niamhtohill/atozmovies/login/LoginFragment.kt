package com.example.niamhtohill.atozmovies.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.DialogInterface
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), LifecycleOwner {
    private lateinit var viewModel: LoginViewModel
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(LoginViewModel::class.java)
        val binding: FragmentLoginBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_login)
        binding.viewModel = viewModel
        binding.setVariable(BR.viewModel, viewModel)
        binding.setLifecycleOwner(viewLifecycleOwner)

        lottieAnimationView = rootView.findViewById(R.id.lottie_film_animation)

        viewModel.accountCreated.observe(this, Observer {
            if (viewModel.accountCreated.value == true) {
                showAccountCreatedDialog()
            }
        })
        viewModel.loading.observe(this, Observer {
            if (viewModel.loading.value!!) {
                lottieAnimationView.playAnimation()
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