package com.socialevoeding.bap.gps

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import com.socialevoeding.bap.domain.model.LocationModel


//Based on https://stackoverflow.com/questions/21085497/how-to-use-android-locationmanager-and-listener/50621540
class GPSTracker (private val context: Context) : Service(), LocationListener {

    var isGPSEnabled = false
    var isNetworkEnabled = false
    var canGetLocation = false
    var location : Location? = null
    var latitude = 0.0
    var longitude = 0.0

    // The minimum distance to change Updates in meters
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 1500F // 10 meters

    // The minimum time between updates in milliseconds
    private val MIN_TIME_BW_UPDATES = 900000 // 30 min
        .toLong()

    // Declaring a Location Manager
    protected var locationManager: LocationManager? = null

    @SuppressLint("MissingPermission") //permission check in MainActivity
    fun startGPSTracker() : LocationModel {
        try {
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
            isNetworkEnabled = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if(!isGPSEnabled && !isNetworkEnabled){
                // no network provider is enabled
            }
            else{
                canGetLocation = true
                if(isNetworkEnabled){
                    // First get location from Network Provider
                    locationManager!!.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this)

                    if (locationManager != null) {
                        location = locationManager!!
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location!!.latitude;
                            longitude = location!!.longitude;
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        if (locationManager != null) {
                            location = locationManager!!
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location!!.latitude;
                                longitude = location!!.longitude;
                            }
                        }
                    }
                }
            }
        }
        catch (e : Exception){

        }
        val model =  LocationModel("", latitude = latitude, longitude = longitude)
        return model
    }

    fun stopUsingGPS() : Boolean {
        locationManager?.removeUpdates(this)
        return true
    }

    fun getCurrentLocation() : LocationModel {
        return LocationModel("", latitude = latitude, longitude = longitude)
    }

    fun getCurrentLatitude() : Double{
        if(location != null){
            latitude = location!!.latitude
        }
        return latitude
    }

    fun getCurrentLongitude() : Double{
        if(location != null){
            longitude = location!!.longitude
        }
        return longitude
    }

    fun canGetLocation(): Boolean {
        return canGetLocation
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onLocationChanged(location: Location?) {}
    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    override fun onProviderEnabled(provider: String?) {}
    override fun onProviderDisabled(provider: String?) {}
}