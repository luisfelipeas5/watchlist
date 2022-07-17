package br.com.luisfelipeas5.watchlist.features.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.luisfelipeas5.watchlist.R
import br.com.luisfelipeas5.watchlist.databinding.FragmentWatchlistBinding
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import br.com.luisfelipeas5.watchlist.features.addmovie.AddMovieFragment
import br.com.luisfelipeas5.watchlist.features.watchlist.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchlistFragment : Fragment() {

    private val onMovieWatched = { movie: Movie, watched: Boolean ->
        onMovieWatchedCallback(movie, watched)
    }

    private var _binding: FragmentWatchlistBinding? = null

    private val viewModel: WatchlistViewModel by viewModels()

    private val moviesAdapter = MoviesAdapter(onMovieWatched)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(AddMovieFragment.ADD_MOVIE_REQUEST_KEY) { _, bundle ->
            onAddMovieResult(bundle)
        }
    }

    private fun onAddMovieResult(bundle: Bundle) {
        val movieAdded = bundle.getParcelable<Movie>(AddMovieFragment.ADD_MOVIE_RESULT_KEY)
        if (movieAdded != null) {
            moviesAdapter.add(movieAdded)
            _binding?.rvMovies?.smoothScrollToPosition(0)
        }
    }

    private fun onMovieUpdated(movie: Movie) {
        moviesAdapter.updated(movie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)

        viewModel.loading.observe(viewLifecycleOwner) { onLoading(it) }
        viewModel.movies.observe(viewLifecycleOwner) { onMoviesReady(it) }
        viewModel.movieUpdated.observe(viewLifecycleOwner) { onMovieUpdated(it) }

        if (moviesAdapter.itemCount == 0) {
            viewModel.loadNextPage()
        }

        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            fabAddMovie.setOnClickListener { onAddMovieButtonClicked() }
            rvMovies.adapter = moviesAdapter
            rvMovies.addOnScrollListener(onScrollListener)

            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(rvMovies)
        }
    }

    private fun onMoviesReady(newMovies: List<Movie>) {
        moviesAdapter.addNewMovies(newMovies)
    }

    private fun onAddMovieButtonClicked() {
        findNavController().navigate(R.id.action_watchlistFragment_to_addMovieFragment)
    }

    private fun onLoading(signingIn: Boolean) {
        _binding?.pbLoading?.visibility = if (signingIn) View.VISIBLE else View.GONE
    }

    private fun onMovieWatchedCallback(movie: Movie, watched: Boolean) {
        viewModel.setMovieWatched(movie, watched)
    }

    private val onScrollListener = object: RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val visibleItemCount = _binding?.rvMovies?.childCount ?: 0
            val totalItemCount = moviesAdapter.itemCount
            val firstVisibleItem = getFirstVisibleItem()

            val loading = viewModel.loading.value ?:false
            if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
                viewModel.loadNextPage()
            }

        }

        private fun getFirstVisibleItem(): Int {
            val layoutManager = _binding?.rvMovies?.layoutManager
            if (layoutManager is LinearLayoutManager) {
                return layoutManager.findFirstVisibleItemPosition()
            }
            return 0
        }
    }

    private val simpleItemTouchCallback = object: ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT,
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return  false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            moviesAdapter.delete(position)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}