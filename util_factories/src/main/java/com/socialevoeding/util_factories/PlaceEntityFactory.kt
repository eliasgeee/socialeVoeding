package com.socialevoeding.util_factories

import com.socialevoeding.data_entities.PlaceEntity
import com.socialevoeding.util_datafactory.DataFactory

object PlaceEntityFactory {
    fun makePlaceEntity(): PlaceEntity {
        return PlaceEntity(
            name = DataFactory.randomString(),
            telephoneNumber = DataFactory.randomString(),
            webUrl = DataFactory.randomString(),
            img = DataFactory.randomString(),
            address = DataFactory.randomString(),
            city = DataFactory.randomString(),
            longitude = 420.0,
            latitude = 420.0
        )
    }

    fun makePlaceEntitiesList(count: Int = 5): List<PlaceEntity> {
        val places = mutableListOf<PlaceEntity>()
        var counter = count
        do {
            places.add(makePlaceEntity())
            counter--
        } while (counter != 0)
        return places
    }
}