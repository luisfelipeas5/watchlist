package br.com.luisfelipeas5.watchlist.features.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.luisfelipeas5.watchlist.databinding.ViewHolderMoviesLoadStateBinding

class MoviesLoadStateAdapter(
    private val retry: () -> Unit,
): LoadStateAdapter<MoviesLoadStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = ViewHolderMoviesLoadStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.binding.apply {
            tvError.isVisible = loadState is LoadState.Error
            btRetry.isVisible = loadState is LoadState.Error
            pbLoading.isVisible = loadState is LoadState.Loading
        }
    }

    inner class ViewHolder(
        val binding: ViewHolderMoviesLoadStateBinding,
    ): RecyclerView.ViewHolder(
        binding.root,
    ) {
        init {
            binding.btRetry.setOnClickListener { retry() }
        }
    }

}