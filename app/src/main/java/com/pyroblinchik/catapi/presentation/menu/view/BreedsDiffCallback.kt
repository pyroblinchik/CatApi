package com.pyroblinchik.catapi.presentation.menu.view

import androidx.recyclerview.widget.DiffUtil
import com.pyroblinchik.catapi.domain.base.models.BreedShort

class BreedsDiffCallback
    () : DiffUtil.ItemCallback<BreedShort>() {

    override fun areItemsTheSame(oldItem: BreedShort, newItem: BreedShort): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BreedShort, newItem: BreedShort): Boolean {

        return oldItem == newItem
    }

}
