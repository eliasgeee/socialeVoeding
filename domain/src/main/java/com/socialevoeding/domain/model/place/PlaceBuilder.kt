package com.socialevoeding.domain.model.place

import com.socialevoeding.domain.model.OpeningDay
import com.socialevoeding.util_models.Either

class PlaceBuilder {
    var name: String = ""
        set(value) {
            if (value.isValidName())
                field = value
            else isValid = false
        }

    var cityName: String = ""
        set(value) {
            if (value.isValidName())
                field = value
            else isValid = false
        }

    var latitude: Double = Double.NaN
        set(value) {
            if (value.isValidLatitude())
                field = value
            else isValid = false
        }

    var longitude: Double = Double.NaN
        set(value) {
            if (value.isValidateLongitude())
                field = value
            else isValid = false
        }

    var openingHours: Array<OpeningDay> = emptyArray()

    var telephoneNumber: String = ""
        set(value) {
            if (value.isValidTelephoneNumber())
                field = value
        }

    var webUrl: String = ""
        set(value) {
            if (value.isValidWebUrl())
                field = value
        }

    var img: String = ""

    var address: String = ""
        set(value) {
            if (value.isValidName())
                field = value
        }

    var isValid: Boolean = true

    fun build(): Either<Unit, Place> {
        return if (checkRequiredFields())
            Either.Right(Place(this))
        else
            Either.Left(Unit)
    }

    private fun checkRequiredFields(): Boolean {
        if (name.trim().isEmpty() || cityName.trim().isEmpty() || latitude.isNaN() || longitude.isNaN())
            isValid = false
        return isValid
    }
}