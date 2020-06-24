package com.socialevoeding.bap.ui.places

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.socialevoeding.bap.R
import com.socialevoeding.domain.model.PlaceLocation
import com.socialevoeding.domain.model.Place

@BindingAdapter("clock_color")
fun ImageView.setClock(place: Place) {
    place.let {
        if (place.isOpen)
            setBackgroundColor(resources.getColor(R.color.pastel_green))
        else
            setBackgroundColor(resources.getColor(R.color.pastel_red))
    }
}

@BindingAdapter("openings_hours_color")
fun TextView.setOpeningshoursColor(place: Place) {
    place.let {
        if (place.isOpen)
            setBackgroundColor(resources.getColor(R.color.pastel_green))
        else {
            setBackgroundColor(resources.getColor(R.color.pastel_red))
            text = "Gesloten"
        }
    }
}

@BindingAdapter("eat_in")
fun TextView.setPlacesStringWithCityName(placeLocation: PlaceLocation?) {
    text = resources.getString(R.string.eat_in_city, placeLocation?.cityName)
}