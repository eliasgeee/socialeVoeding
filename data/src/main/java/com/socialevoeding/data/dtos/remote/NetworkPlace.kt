package com.socialevoeding.data.dtos.remote

import com.socialevoeding.data.dtos.remote.NetworkCoordinates
import com.socialevoeding.data.dtos.remote.NetworkOpeningDay

data class NetworkPlace(
    var name : String = "",
    val networkCoordinates: NetworkCoordinates = NetworkCoordinates(
        0.0,
        0.0
    ),
    val rating : Float = 0F,
    val url : String = ""
){
    var img_url : String = ""
    var address : String = ""
    var telephoneNumber : String = ""
    var openingHours = ArrayList<NetworkOpeningDay>(emptyList())
}
