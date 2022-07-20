package br.com.luisfelipeas5.watchlist.data.datasources.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie

class MoviesPagingSource(
    private val remoteDataSource: RemoteDataSource,
): PagingSource<Int, Movie>() {

    companion object {
        private const val START_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key ?: START_PAGE_INDEX
        val watchlist = getData(pageIndex)
        return LoadResult.Page(
            data = watchlist,
            prevKey = getPrevKey(pageIndex),
            nextKey = getNextKey(pageIndex, watchlist),
        )
    }

    private suspend fun getData(
        pageIndex: Int,
    ) = remoteDataSource.getWatchlist(pageIndex = pageIndex)

    private fun getPrevKey(pageIndex: Int) =
        if (pageIndex == START_PAGE_INDEX) null else pageIndex - 1

    private fun getNextKey(pageIndex: Int, watchlist: List<Movie>) =
        if (watchlist.isNotEmpty()) pageIndex + 1 else null

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        return anchorPosition / state.config.pageSize
    }
}