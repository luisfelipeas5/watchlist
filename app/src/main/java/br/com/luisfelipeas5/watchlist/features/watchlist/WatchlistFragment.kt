package br.com.luisfelipeas5.watchlist.features.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.luisfelipeas5.watchlist.R
import br.com.luisfelipeas5.watchlist.databinding.FragmentWatchlistBinding
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import br.com.luisfelipeas5.watchlist.features.addmovie.AddMovieFragment
import br.com.luisfelipeas5.watchlist.features.watchlist.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
            viewModel.addMovie(movieAdded)
            _binding?.rvMovies?.smoothScrollToPosition(0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)

        viewModel.movies.observe(viewLifecycleOwner) { onMoviesReady(it) }
        observeLoading()

        return _binding?.root
    }

    private fun observeLoading() {
        lifecycleScope.launch {
            moviesAdapter.loadStateFlow.collect {
                _binding?.pbLoading?.isVisible = it.source.refresh is LoadState.Loading ||
                        it.source.append is LoadState.Loading
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.apply {
            fabAddMovie.setOnClickListener { onAddMovieButtonClicked() }
            rvMovies.adapter = moviesAdapter

            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(rvMovies)
        }
    }

    private fun onMoviesReady(moviesPagingData: PagingData<Movie>?) {
        lifecycleScope.launch {
            moviesPagingData?.let {
                moviesAdapter.submitData(it)
            }
        }
    }

    private fun onAddMovieButtonClicked() {
        findNavController().navigate(R.id.action_watchlistFragment_to_addMovieFragment)
    }

    private fun onMovieWatchedCallback(movie: Movie, watched: Boolean) {
        viewModel.setMovieWatched(movie, watched)
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
            val position = viewHolder.bindingAdapterPosition
            viewModel.removeMovie(position)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}