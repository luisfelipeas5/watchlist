package br.com.luisfelipeas5.watchlist.data.repository

import br.com.luisfelipeas5.watchlist.data.datasources.remote.RemoteDataSource
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) {
    suspend fun getWatchlist(pageIndex: Int): List<Movie> {
        return remoteDataSource.getWatchlist(pageIndex)
    }
}