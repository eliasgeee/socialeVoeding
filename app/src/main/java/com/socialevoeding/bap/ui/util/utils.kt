package com.socialevoeding.bap.ui.util

fun createKilometerLabelFromDistanceInMeters(distance : Int) : String{
    return (distance / 1000) .toString()
}