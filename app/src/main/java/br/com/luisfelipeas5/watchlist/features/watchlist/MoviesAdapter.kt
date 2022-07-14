package br.com.luisfelipeas5.watchlist.features.watchlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.luisfelipeas5.watchlist.databinding.ViewHolderMoviesBinding
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import com.bumptech.glide.Glide

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

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

        val movie = movies[position]
        holder.binding.apply {
            tvTitle.text = movie.title
            tvWhoRecommended.text = movie.whoRecommended
            cbWatched.isChecked = movie.watched

            val coverPath = "https://image.tmdb.org/t/p/w780${movie.cover}"
            Glide.with(ivCover)
                .load(coverPath)
                .centerCrop()
                .into(ivCover)
        }
    }

    override fun getItemCount() = movies.size

    fun addNewMovies(newMovies: List<Movie>) {
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    fun add(movieAdded: Movie) {
        movies.add(0, movieAdded)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        val binding: ViewHolderMoviesBinding
    ): RecyclerView.ViewHolder(binding.root)
}
