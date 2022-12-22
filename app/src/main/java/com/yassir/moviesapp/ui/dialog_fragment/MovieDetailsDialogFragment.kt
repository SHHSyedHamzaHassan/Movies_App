package com.yassir.moviesapp.ui.dialog_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.yassir.moviesapp.databinding.MovieDetailsDialogFragmentBinding
import com.yassir.moviesapp.utils.CommonLoader
import com.yassir.moviesapp.utils.setLoaderToFullScreen
import com.yassir.moviesapp.viewmodel.MovieDetailsViewModel
import com.yassir.moviesapp.wrappers.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsDialogFragment : DialogFragment() {

    //#region instances
    private lateinit var binding: MovieDetailsDialogFragmentBinding
    private val viewModel: MovieDetailsViewModel by viewModels()
    val args: MovieDetailsDialogFragmentArgs by navArgs()
    //#endregion instances

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailsDialogFragmentBinding.inflate(inflater, container, false)
        registerObservableFields()
        binding.ivBack.setOnClickListener { backButton() }
        return binding.root
    }

    private fun registerObservableFields() {
        viewModel.shareData.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                MovieDetailsViewModel.APIDataShareToFragment.MOVIE_DETAILS -> {
                    binding.apply { movieDetail = viewModel.getMovieDetailsResposne() }
                }
            }
        })
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                CommonLoader.showLoader(requireActivity())
            else
                CommonLoader.dismissLoader()
        }
        viewModel.getMovieId(args.movieId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setLoaderToFullScreen(this)
        super.onCreate(savedInstanceState);
    }

    fun backButton() {
        dismiss()
    }
}