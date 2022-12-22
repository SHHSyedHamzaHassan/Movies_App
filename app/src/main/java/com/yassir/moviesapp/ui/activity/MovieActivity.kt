package com.yassir.moviesapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yassir.moviesapp.databinding.MovieActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: MovieActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}