package com.example.niamhtohill.atozmovies.home.topMovies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.niamhtohill.atozmovies.R
import com.example.niamhtohill.atozmovies.api.Models
import com.example.niamhtohill.atozmovies.home.HomeViewModel
import com.squareup.picasso.Picasso

class ActorAdapter(var context: Context, private val movieCast: List<Models.MovieCastMember>, private val viewModel: HomeViewModel) : RecyclerView.Adapter<ActorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_actor_cell, parent, false))
    }

    override fun getItemCount(): Int {
        return movieCast.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val castMember = movieCast[position]
        holder.actorName.text = castMember.name
        holder.actorCharacter.text = castMember.character
        if (castMember.profile_path != null) {
            Picasso.get().load("http://image.tmdb.org/t/p/w185" + castMember.profile_path).into(holder.actorImageView)
        }
    }
}