package com.socialevoeding.util_factories

import com.socialevoeding.domain.model.place.MIN_LENGTH_NAME
import com.socialevoeding.domain.model.place.Place
import com.socialevoeding.domain.model.place.buildPlace
import com.socialevoeding.util_datafactory.DataFactory
import com.socialevoeding.util_models.Either

object PlaceFactory {
    fun makePlace(): Either<Unit, Place> {
        return buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 1) }
            longitude { 50.0 }
            latitude { 50.0 }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 1) }
        }
    }

    fun makePlacesList(count: Int = 5): List<Place> {
        val places = mutableListOf<Place>()
        var counter = count
        do {
            when (val place = makePlace()) {
                is Either.Right -> places.add(place.b)
                is Either.Left -> counter++
            }
            counter--
        } while (counter != 0)
        return places
    }
}