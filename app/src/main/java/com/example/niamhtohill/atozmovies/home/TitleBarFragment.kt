package com.example.niamhtohill.atozmovies.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.niamhtohill.atozmovies.R

class TitleBarFragment : Fragment() {

    lateinit var logoutButton: ImageButton
    lateinit var titleBarTextView: TextView
    lateinit var parentBaseActivity: HomeActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parentBaseActivity = activity as HomeActivity
        val rootView = inflater.inflate(R.layout.fragment_title_bar, container, false)
        logoutButton = rootView.findViewById(R.id.logout_button)
        titleBarTextView = rootView.findViewById(R.id.title_text_view)
        logoutButton.setOnClickListener {
            parentBaseActivity.viewModel.onLogoutClicked(it)
        }
        return rootView
    }
}