package com.yassir.moviesapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yassir.moviesapp.databinding.RowMovieListBinding
import com.yassir.moviesapp.models.ResultsItem

class MovieListAdapter(val context: Context) :
    RecyclerView.Adapter<MovieListAdapter.MYViewHolder>() {

    private lateinit var binding: RowMovieListBinding
    var items = ArrayList<ResultsItem>()
    private var listener :((ResultsItem)->Unit)?=null

    fun setListdata(data: List<ResultsItem>) {
        this.items = data as ArrayList<ResultsItem>
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MYViewHolder {
        binding = RowMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MYViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MYViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        binding.apply { movie = items[position] }
        holder.itemView.rootView.setOnClickListener {
            listener?.let {
                it(items[position])
            }
        }
    }

    fun itemClickListener(l:(ResultsItem)->Unit){
        listener= l
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MYViewHolder(val binding: RowMovieListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}