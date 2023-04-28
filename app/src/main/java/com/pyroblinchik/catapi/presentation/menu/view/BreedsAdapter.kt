package com.pyroblinchik.catapi.presentation.menu.view

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.pyroblinchik.catapi.databinding.ListItemBreedBinding
import com.pyroblinchik.catapi.domain.base.models.BreedShort

class BreedsAdapter(
    private val onItemClickListener: ((breed: BreedShort) -> Unit)
) : ListAdapter<BreedShort, BreedsHolder>(BreedsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsHolder {
        val binding = ListItemBreedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BreedsHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedsHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                breedName.text = name

                root.setOnClickListener{ onItemClickListener.invoke(item) }
            }
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}
