package br.com.luisfelipeas5.watchlist.features.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.luisfelipeas5.watchlist.arch.BaseViewModel
import br.com.luisfelipeas5.watchlist.data.repository.Repository
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val repository: Repository,
) : BaseViewModel() {
    val loading: LiveData<Boolean> = MutableLiveData()

    val movies: LiveData<List<Movie>> = MutableLiveData()

    val movieUpdated: LiveData<Movie> = MutableLiveData()

    private var pageIndex = 0
    private var maxPageReached = false

    fun loadNextPage() {
        if (maxPageReached) return

        viewModelScope.launch {
            loading.postValue(true)

            val moviesPage = repository.getWatchlist(pageIndex)

            maxPageReached = moviesPage.isEmpty()
            pageIndex++

            movies.postValue(moviesPage)

            loading.postValue(false)
        }
    }

    fun setMovieWatched(movie: Movie, watched: Boolean) {
        viewModelScope.launch {
            try {
                movie.watched = watched
                repository.updateMovie(movie)
            } catch (exception: Throwable) {
                movie.watched = !watched
            }
            movieUpdated.postValue(movie)
        }
    }

}