package br.com.luisfelipeas5.watchlist.features.watchlist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.luisfelipeas5.watchlist.databinding.ViewHolderMoviesBinding
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import com.bumptech.glide.Glide

class MoviesAdapter(
    private val onMovieWatched: (movie: Movie, watched: Boolean) -> Unit,
): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

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

            Glide.with(ivCover)
                .load(movie.cover)
                .centerCrop()
                .into(ivCover)
        }
    }

    override fun getItemCount() = movies.size

    fun addNewMovies(newMovies: List<Movie>) {
        val oldList = mutableListOf<Movie>().apply {
            addAll(movies)
        }
        movies.addAll(newMovies)

        val callback = MoviesDiffUtilCallback(oldList, movies)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
    }

    fun add(movieAdded: Movie) {
        val oldList = mutableListOf<Movie>().apply {
            addAll(movies)
        }
        movies.add(0, movieAdded)

        val callback = MoviesDiffUtilCallback(oldList, movies)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
    }

    //TODO Something wrong here!
    fun updated(movie: Movie) {
        val oldList = mutableListOf<Movie>().apply {
            addAll(movies)
        }

        val indexOf = movies.indexOfFirst { it.title == movie.title }
        movies[indexOf] = movie

        val callback = MoviesDiffUtilCallback(oldList, movies)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
    }

    fun delete(position: Int) {
        val oldList = mutableListOf<Movie>().apply {
            addAll(movies)
        }

        movies.removeAt(position)

        val callback = MoviesDiffUtilCallback(oldList, movies)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
    }

    inner class ViewHolder(
        val binding: ViewHolderMoviesBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cbWatched.setOnCheckedChangeListener { _, checked ->
                val movie = movies[adapterPosition]
                if (checked != movie.watched) {
                    onMovieWatched(movie, checked)
                }
            }
        }

    }
}
