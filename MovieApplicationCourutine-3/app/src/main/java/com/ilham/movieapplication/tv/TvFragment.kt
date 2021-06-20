package com.ilham.movieapplication.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilham.movieapplication.core.ui.TvAdapter
import com.ilham.movieapplication.databinding.FragmentTvBinding
import com.ilham.movieapplication.detail.DetailTvActivity
import com.ilham.movieapplication.core.data.Resource
import org.koin.android.viewmodel.ext.android.viewModel


class TvFragment : Fragment() {

    private val tvViewModel: TvViewModel by viewModel()

    private var _fragmentTvBinding: FragmentTvBinding? = null
    private val binding get() = _fragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
    //        val factory = ViewModelFactoryTvShow.getInstance(requireActivity())
    //         tvViewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            val tvAdapter = TvAdapter()
            tvAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailTvActivity::class.java)
                intent.putExtra(DetailTvActivity.EXTRA_TV, selectedData)
                startActivity(intent)
            }

            tvViewModel.getTvShows.observe(viewLifecycleOwner, { tv ->
                if (tv != null) {
                    when (tv) {
                        is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            tvAdapter.setTvShow(tv.data)
                            tvAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding?.rvTv) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tvAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvBinding = null
    }


}