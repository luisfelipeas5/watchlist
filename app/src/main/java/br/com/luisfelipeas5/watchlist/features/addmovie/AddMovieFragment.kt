package br.com.luisfelipeas5.watchlist.features.addmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.luisfelipeas5.watchlist.databinding.FragmentAddMovieBinding

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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}