package com.socialevoeding.bap.ui.places

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.socialevoeding.bap.databinding.RvPlaceItemBinding
import com.socialevoeding.presentation_android.ViewItem
import com.socialevoeding.util_android.createKilometerLabelFromDistanceInMeters

class PlacesAdapter(private val context: Context, private val clickListener: PlacesClickListener) :
    ListAdapter<ViewItem.PlaceViewItem, PlacesAdapter.PlacesViewHolder>(
        PlacesDiffCallback()
    ) {

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.fillViewItems(getItem(position)!!, clickListener, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        return PlacesViewHolder.from(
            parent,
            viewType
        )
    }

    class PlacesViewHolder private constructor(val binding: RvPlaceItemBinding, private val viewType: Int) : RecyclerView.ViewHolder(binding.root) {

        fun fillViewItems(
            location: ViewItem.PlaceViewItem,
            clickListener: PlacesClickListener,
            context: Context
        ) {
            binding.clicklistener = clickListener
            binding.place = location

            binding.placeItem.setOnClickListener {
                clickListener.onPlaceClick(location)
            }

            binding.txtAdres.text = location.address
            val distance =
                location.distance?.let {
                    com.socialevoeding.util_android.createKilometerLabelFromDistanceInMeters(
                        it
                    )
                }
            binding.txtDistance.text = "$distance km"

            if (location.img!!.isNotEmpty()) {
                val decodedString: ByteArray = Base64.decode(location.img, Base64.DEFAULT)
                binding.imgPlace.load(bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size))
                //  binding.imgPlace.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size))
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

class PlacesDiffCallback : DiffUtil.ItemCallback<ViewItem.PlaceViewItem>() {
    override fun areItemsTheSame(oldLocation: ViewItem.PlaceViewItem, newLocation: ViewItem.PlaceViewItem): Boolean {
        return newLocation.longitude == oldLocation.longitude && newLocation.latitude == oldLocation.latitude
    }

    override fun areContentsTheSame(oldLocation: ViewItem.PlaceViewItem, newLocation: ViewItem.PlaceViewItem): Boolean {
        return newLocation.longitude == oldLocation.longitude && newLocation.latitude == oldLocation.latitude
    }
}

interface PlacesClickListener {
    fun onPlaceClick(place: ViewItem.PlaceViewItem)
}