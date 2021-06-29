package com.h5190063_gorkemaydogdu_but.ui.disneylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190063_gorkemaydogdu_but.data.model.DisneyResponseItem
import com.h5190063_gorkemaydogdu_but.databinding.CardviewListBinding
import com.h5190063_gorkemaydogdu_but.util.GlideUtil

import com.h5190063_gorkemaydogdu_but.util.ItemClickListener

class DisneyAdapter(var filmler: List<DisneyResponseItem>, var onItemClickListener: ItemClickListener) : RecyclerView.Adapter<DisneyAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CardviewListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardviewListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                binding.textViewKarakterAdi.text = filmler[position].name
                binding.textViewID.text = filmler[position].id.toString()
                binding.textViewPlayTime.text = filmler[position].playtime
                GlideUtil().apply {
                    imageViewPosterResim.resimCek(filmler[position].poster)
                }
                filmCard.setOnClickListener {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return filmler.size
    }
}
