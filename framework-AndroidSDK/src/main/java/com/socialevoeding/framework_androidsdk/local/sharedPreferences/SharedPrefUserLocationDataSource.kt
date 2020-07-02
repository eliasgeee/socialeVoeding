package com.socialevoeding.framework_androidsdk.local.sharedPreferences

import android.content.SharedPreferences
import com.socialevoeding.data.datasources.local.cache.UserLocationCacheDataSource
import com.socialevoeding.data.dtos.local.device.UserLocationDTO
import com.socialevoeding.util_models.Either
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

const val locationKey = "currentLocationUser"

class SharedPrefUserLocationDataSource(
    private val cache: SharedPreferences,
    private val moshi: Moshi
) : UserLocationCacheDataSource {

    private var jsonAdapter : JsonAdapter<UserLocationDTO>? = null

    override fun storeLastKnownUserLocation(currentLocation: UserLocationDTO) {
        clearLastKnownUserLocation()
        jsonAdapter = moshi.adapter(UserLocationDTO::class.java)
        cache.edit().putString(locationKey, jsonAdapter!!.toJson(currentLocation)).apply()
    }

    override fun getLastKnownUserLocation(): Either<Unit, UserLocationDTO> {
        jsonAdapter = moshi.adapter(UserLocationDTO::class.java)
        cache.getString(locationKey, null).apply {
            return if (this == null)
                Either.Left(Unit)
            else
                Either.Right(jsonAdapter!!.fromJson(this)!!)
        }
    }

    private fun clearLastKnownUserLocation() {
        cache.edit().clear().apply()
    }
}