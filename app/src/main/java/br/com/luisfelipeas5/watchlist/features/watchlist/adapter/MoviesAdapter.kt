package br.com.luisfelipeas5.watchlist.features.watchlist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.luisfelipeas5.watchlist.databinding.ViewHolderMoviesBinding
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import com.bumptech.glide.Glide

class MoviesAdapter(
    private val onMovieWatched: (movie: Movie, watched: Boolean) -> Unit,
): PagingDataAdapter<Movie, MoviesAdapter.ViewHolder>(MoviesDiffUtilItemCallback()) {

    private var onCreateViewHolderCounter = 0
    private var onBindViewViewHolderCounter = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        onCreateViewHolderCounter++
        Log.d("MoviesAdapter", "onCreateViewHolder counter = $onCreateViewHolderCounter")

        val binding = ViewHolderMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindViewViewHolderCounter++
        Log.d("MoviesAdapter", "onBindViewHolder " +
                "position = $position " +
                "counter = $onBindViewViewHolderCounter")

        val movie = getItem(position)
        holder.binding.apply {
            tvTitle.text = movie?.title
            tvWhoRecommended.text = movie?.whoRecommended
            cbWatched.isChecked = movie?.watched ?: false

            Glide.with(ivCover)
                .load(movie?.cover)
                .centerCrop()
                .into(ivCover)
        }
    }

    inner class ViewHolder(
        val binding: ViewHolderMoviesBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cbWatched.setOnCheckedChangeListener { _, checked ->
                getItem(bindingAdapterPosition)?.let { movie ->
                    if (checked != movie.watched) {
                        onMovieWatched(movie, checked)
                    }
                }

            }
        }

    }
}
