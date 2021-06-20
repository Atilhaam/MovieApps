package com.ilham.movieapplication.favorite.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilham.movieapplication.core.ui.TvFavoriteAdapter
import com.ilham.movieapplication.detail.DetailTvActivity
import com.ilham.movieapplication.favorite.databinding.FragmentTvFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel


class TvFavoriteFragment : Fragment() {
    private var _fragmentTvFavoriteBinding: FragmentTvFavoriteBinding? = null
    private val binding get() = _fragmentTvFavoriteBinding

    private val tvFavoriteViewModel: TvFavoriteViewModel by viewModel()
    private lateinit var adapter: TvFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _fragmentTvFavoriteBinding = FragmentTvFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            adapter = TvFavoriteAdapter()
            adapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailTvActivity::class.java)
                intent.putExtra(DetailTvActivity.EXTRA_TV, selectedData)
                startActivity(intent)
            }

            binding?.progressBar?.visibility = View.VISIBLE
            tvFavoriteViewModel.favoriteTvShow.observe(viewLifecycleOwner, { tv ->
                if (tv.isEmpty()) {
                    showNotAvailable(true)
                } else {
                    showNotAvailable(false)
                }
                binding?.progressBar?.visibility = View.GONE
                adapter.setTvShow(tv)
                adapter.notifyDataSetChanged()
            })

            binding?.rvTv?.layoutManager = LinearLayoutManager(context)
            binding?.rvTv?.setHasFixedSize(true)
            binding?.rvTv?.adapter = adapter
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