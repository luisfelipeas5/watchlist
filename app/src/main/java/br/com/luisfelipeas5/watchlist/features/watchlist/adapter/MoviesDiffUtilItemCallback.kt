package br.com.luisfelipeas5.watchlist.features.watchlist.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie

class MoviesDiffUtilItemCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.watched == newItem.watched &&
                oldItem.cover == newItem.cover &&
                oldItem.whoRecommended == newItem.whoRecommended
    }
}