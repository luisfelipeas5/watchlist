package br.com.luisfelipeas5.watchlist.features.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import br.com.luisfelipeas5.watchlist.arch.BaseViewModel
import br.com.luisfelipeas5.watchlist.data.repository.Repository
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 10

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val repository: Repository,
) : BaseViewModel() {

    val movies: LiveData<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = {
            //always returns a new instance
            repository.getWatchlist()
        }
    ).liveData

    private val _movies = mutableListOf<Movie>()

    fun setMovieWatched(movie: Movie, watched: Boolean) {
        viewModelScope.launch {
            try {
                movie.watched = watched
                repository.updateMovie(movie)
            } catch (exception: Throwable) {


                val movieUpdated = movie.copyWith(
                    watched = !watched,
                )
                val indexOf = _movies.indexOfFirst { it.title == movie.title }
                _movies[indexOf] = movieUpdated
                postMovies()
            }
        }
    }

    fun addMovie(movie: Movie) {
        _movies.add(0, movie)
        postMovies()
    }

    fun removeMovie(index: Int) {
        _movies.removeAt(index)
        postMovies()
    }

    private fun postMovies() {
        val moviesCopied = _movies.toList()
//        movies.postValue(moviesCopied)
    }

}