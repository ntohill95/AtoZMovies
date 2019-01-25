package com.example.niamhtohill.atozmovies.home.recommendation

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.niamhtohill.atozmovies.BR
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.databinding.FragmentRecommendationBinding
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.example.niamhtohill.atozmovies.home.MyViewModelFactory

class RecommendationFragment : Fragment() {

    private var fakeList = ArrayList<String>()
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(requireActivity().application)).get(HomeViewModel::class.java)
        val binding: FragmentRecommendationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recommendation, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        val rootView = binding.root

        val listView: RecyclerView = rootView.findViewById(R.id.recommendations_list_view)
        listView.layoutManager = LinearLayoutManager(this.context)
        listView.adapter = RecommendationAdapter(context!!, generatedFakeData())
        return rootView
    }

    private fun generatedFakeData(): ArrayList<String> {
        fakeList.clear()
        fakeList.add("Recommendation 1")
        fakeList.add("Recommendation 2")
        fakeList.add("Recommendation 3")
        fakeList.add("Recommendation 4")
        fakeList.add("Recommendation 5")
        fakeList.add("Recommendation 6")
        fakeList.add("Recommendation 7")
        fakeList.add("Recommendation 8")
        fakeList.add("Recommendation 9")
        fakeList.add("Recommendation 10")
        return fakeList
    }
}