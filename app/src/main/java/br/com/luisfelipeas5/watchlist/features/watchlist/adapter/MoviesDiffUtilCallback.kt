package br.com.luisfelipeas5.watchlist.features.watchlist.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie

class MoviesDiffUtilCallback(
    private val oldList: List<Movie>,
    private val newList: List<Movie>,
): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.title == new.title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.title == new.title &&
            old.watched == new.watched &&
            old.cover == new.cover &&
            old.whoRecommended == new.whoRecommended
    }
}