package com.example.niamhtohill.atozmovies.home.topMovies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_actor_cell.view.*

class ActorViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var actorImageView = view.actor_image_view
    var actorName = view.actor_name_textView
    var actorCharacter = view.actor_character_textView
}