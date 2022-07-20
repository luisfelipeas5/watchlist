package br.com.luisfelipeas5.watchlist.data.repository

import br.com.luisfelipeas5.watchlist.data.datasources.remote.MoviesPagingSource
import br.com.luisfelipeas5.watchlist.data.datasources.remote.RemoteDataSource
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import kotlinx.coroutines.delay
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {
    fun getWatchlist(): MoviesPagingSource {
        return MoviesPagingSource(
            remoteDataSource
        )
    }

    suspend fun updateMovie(movie: Movie) {
        delay(1500)
        throw Exception("Movie update failed")
    }
}