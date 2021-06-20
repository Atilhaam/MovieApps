package com.ilham.movieapplication.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilham.movieapplication.core.ui.MovieFavoriteAdapter
import com.ilham.movieapplication.detail.DetailMovieActivity
import com.ilham.movieapplication.favorite.databinding.FragmentMovieFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MovieFavoriteFragment : Fragment() {
    private var _fragmentMovieFavoriteBinding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _fragmentMovieFavoriteBinding

    private val movieFavoriteViewModel: MovieFavoriteViewModel by viewModel()
    private lateinit var adapter: MovieFavoriteAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _fragmentMovieFavoriteBinding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null) {

            adapter = MovieFavoriteAdapter()
            adapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, selectedData)
                startActivity(intent)
            }
            binding?.progressBar?.visibility = View.VISIBLE
            movieFavoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, { movies ->
                if (movies.isEmpty()) {
                    showNotAvailable(true)
                } else {
                    showNotAvailable(false)
                }
                binding?.progressBar?.visibility = View.GONE
                adapter.setMovies(movies)
                adapter.notifyDataSetChanged()
            })

            binding?.rvMovie?.layoutManager = LinearLayoutManager(context)
            binding?.rvMovie?.setHasFixedSize(true)
            binding?.rvMovie?.adapter = adapter
        }
    }
    private fun showNotAvailable(state: Boolean) {
        if (state) {
            binding?.tvNotavail?.visibility = View.VISIBLE
        } else {
            binding?.tvNotavail?.visibility = View.GONE
        }
    }
}