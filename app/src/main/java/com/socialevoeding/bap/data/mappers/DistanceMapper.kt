package com.socialevoeding.bap.data.mappers

import android.location.Location

object DistanceMapper {

        fun getDistanceBetweenTwoLocationsInMeters(
            lat1: Double,
            lon1: Double,
            lat2: Double,
            lon2: Double
        ): Float {
            val distance = FloatArray(2)
            Location.distanceBetween(
                lat1, lon1,
                lat2, lon2, distance
            )
            return distance[0]
        }
}
