package com.yassir.moviesapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yassir.moviesapp.adapters.MovieListAdapter
import com.yassir.moviesapp.databinding.MovieFragmentBinding
import com.yassir.moviesapp.utils.CommonAlert.Companion.setAlert
import com.yassir.moviesapp.utils.CommonLoader
import com.yassir.moviesapp.viewmodel.MovieViewModel
import com.yassir.moviesapp.wrappers.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    //#region instance variable
    private lateinit var binding: MovieFragmentBinding
    private val viewModel: MovieViewModel by viewModels()
    lateinit var movieListAdapter: MovieListAdapter

    //#endregion instance variable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false).apply { vm = viewModel }
        initRecyclerViews()
        return binding.root
    }

    private fun initRecyclerViews() {
        binding.rvMovieList.apply {
            movieListAdapter =
                MovieListAdapter(activity?.applicationContext!!)
            adapter = movieListAdapter
        }
        movieListAdapter.itemClickListener {
            findNavController().navigate(
                MovieFragmentDirections.actionMovieFragmentToMovieDetailsDialogFragment(it.id!!)
            )
        }
        registerObservableFields()
    }

    private fun registerObservableFields() {
        viewModel.shareData.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                MovieViewModel.APIDataShareToFragment.MOVIE_LIST -> {
                    movieListAdapter.setListdata(viewModel.getMovieListData().results!!)
                    movieListAdapter.notifyDataSetChanged()
                }
                MovieViewModel.APIDataShareToFragment.ERROR -> {
                   setAlert(activity!!,viewModel.errorMessage)
                }
            }
        })
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                CommonLoader.showLoader(requireActivity())
            else
                CommonLoader.dismissLoader()
        }

    }


}