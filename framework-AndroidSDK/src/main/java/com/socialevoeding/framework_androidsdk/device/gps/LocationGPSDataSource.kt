package com.socialevoeding.framework_androidsdk.device.gps

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import com.socialevoeding.data.datasources.device.CurrentLocationDataSource
import com.socialevoeding.data.dtos.local.device.CoordinatesDTO

// Based on https://stackoverflow.com/questions/21085497/how-to-use-android-locationmanager-and-listener/50621540
class LocationGPSDataSource(
    private val locationManager: LocationManager
) :
    //Service(), LocationListener,
    CurrentLocationDataSource {

    private var isGPSEnabled = false
    private var isNetworkEnabled = false
    private var canGetLocation = false
    private var location: Location? = null
    private var latitude = 0.0
    private var longitude = 0.0

    // The minimum distance to change Updates in meters
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 1500F

    // The minimum time between updates in milliseconds
    private val MIN_TIME_BW_UPDATES = 900000.toLong()

    @SuppressLint("MissingPermission")
    private fun startGPSTracker() {
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                canGetLocation = true
                if (isNetworkEnabled) {
                    // First get location from Network Provider
                    /*locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this)
*/
                        location = locationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            latitude = location!!.latitude
                            longitude = location!!.longitude
                        }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        /*locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this)*/
                            location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                latitude = location!!.latitude
                                longitude = location!!.longitude
                            }
                    }
                }
            }
    }

    private fun stopUsingGPS() {
        //locationManager.removeUpdates(this)
    }

    override fun getCurrentLocation(): CoordinatesDTO {
        startGPSTracker()
        val coordinates = CoordinatesDTO(latitude = latitude, longitude = longitude)
        stopUsingGPS()
        return coordinates
    }

    /*override fun onBind(intent: Intent?): IBinder? { return null }
    override fun onLocationChanged(location: Location?) {}
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    override fun onProviderEnabled(provider: String?) {}
    override fun onProviderDisabled(provider: String?) {}*/
}