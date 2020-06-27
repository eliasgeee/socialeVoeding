package com.socialevoeding.util_factories

import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.model.Place

object PlaceFactory{
    fun makePlace(): Place {
        return Place(
            name = DataFactory.randomString(),
            telephoneNumber = DataFactory.randomString(),
            webUrl = DataFactory.randomString(),
            img = DataFactory.randomString(),
            address = DataFactory.randomString(),
            cityName = DataFactory.randomString(),
            longitude = 420.0,
            latitude = 420.0,
            openingHours = emptyArray()
        )
    }

    fun makePlacesList(count: Int = 5): List<Place> {
        val places = mutableListOf<Place>()
        var counter = count
        do {
            places.add(makePlace())
            counter--
        } while (counter != 0)
        return places
    }
}