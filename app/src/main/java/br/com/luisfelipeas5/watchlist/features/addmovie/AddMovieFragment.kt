package br.com.luisfelipeas5.watchlist.features.addmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import br.com.luisfelipeas5.watchlist.databinding.FragmentAddMovieBinding
import br.com.luisfelipeas5.watchlist.domain.entities.movies.Movie

class AddMovieFragment: Fragment() {

    companion object {
        const val ADD_MOVIE_REQUEST_KEY = "ADD_MOVIE_REQUEST_KEY"
        const val ADD_MOVIE_RESULT_KEY = "ADD_MOVIE_RESULT_KEY"
    }

    private var _binding: FragmentAddMovieBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddMovieBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.btAdd?.setOnClickListener { onButtonClickListener() }
    }

    private fun onButtonClickListener() {
        _binding?.apply {
            val movie = Movie(
               title = etTitle.text.toString(),
               whoRecommended = etWhoRecommended.text.toString(),
                watched = false,
                cover = etCoverUrl.text.toString(),
            )
            onCreated(movie)
        }
    }


    private fun onCreated(movie: Movie) {
        setFragmentResult(ADD_MOVIE_REQUEST_KEY, bundleOf(ADD_MOVIE_RESULT_KEY to movie))
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}