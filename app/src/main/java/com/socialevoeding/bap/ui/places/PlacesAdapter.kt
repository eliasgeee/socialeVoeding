package com.socialevoeding.bap.ui.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.socialevoeding.bap.databinding.RvPlaceItemBinding
import com.socialevoeding.bap.domain.model.Place

class PlacesAdapter(private val clickListener: PlacesClickListener) :
    ListAdapter<Place, PlacesAdapter.PlacesViewHolder>(
        PlacesDiffCallback()
    ) {

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.fillViewItems(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        return PlacesViewHolder.from(
            parent,
            viewType
        )
    }

    class PlacesViewHolder private constructor(val binding: RvPlaceItemBinding, private val viewType: Int) : RecyclerView.ViewHolder(binding.root) {

        fun fillViewItems(
            location: Place,
            clickListener: PlacesClickListener
        ) {
            binding.clicklistener = clickListener
            binding.place = location

            binding.placeItem.setOnClickListener {
                clickListener.onPlaceClick(location)
            }

            binding.txtAdres.text = location.address
            binding.txtDistance.text = location.distance.toString() + "km"

//            binding.imgPlacesFoodtype.background = null

            when (location) {
                //    is FoodLocation -> {
                //            Glide.with(binding.imgPlacesFoodtype).load(location.filterCat.thumb).into(binding.imgPlacesFoodtype)
                //     }
            }

            binding.txtPlacesName.text = location.name

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, viewType: Int): PlacesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvPlaceItemBinding.inflate(layoutInflater, parent, false)
                return PlacesViewHolder(
                    binding,
                    viewType
                )
            }
        }
    }
}

class PlacesDiffCallback : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldLocation: Place, newLocation: Place): Boolean {
        return newLocation.id == oldLocation.id
    }

    override fun areContentsTheSame(oldLocation: Place, newLocation: Place): Boolean {
        return newLocation.id == oldLocation.id
    }
}

interface PlacesClickListener {
    fun onPlaceClick(place: Place)
}