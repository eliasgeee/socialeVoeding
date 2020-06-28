package com.socialevoeding.domain.model.place

import com.socialevoeding.domain.model.OpeningDay
import com.socialevoeding.util_models.Either

const val MIN_LENGTH_NAME = 2
const val MIN_LENGTH_PHONE_NUMBER = 3

class PlaceBuilder {
    var name: String = ""
        private set

    var cityName: String = ""
        private set

    var latitude: Double = Double.NaN
        private set

    var longitude: Double = Double.NaN
        private set

    var openingHours: Array<OpeningDay>? = emptyArray()
        private set

    var telephoneNumber: String? = ""
        private set

    var webUrl: String? = ""
        private set

    var img: String? = ""
        private set

    var address: String? = ""
        private set

    var isValid: Boolean = true

    inline fun name(name: () -> String) {
        if (name().length >= MIN_LENGTH_NAME)
            this.`access$name` = name()
        else isValid = false
    }

    inline fun cityName(cityName: () -> String) {
        if (cityName().length >= MIN_LENGTH_NAME)
            this.`access$cityName` = cityName()
        else isValid = false
    }

    inline fun latitude(latitude: () -> Double) {
        if (latitude().isValidLatitude())
            this.`access$latitude` = latitude()
        else isValid = false
    }

    inline fun longitude(longitude: () -> Double) {
        if (longitude().isValidateLongitude())
            this.`access$longitude` = longitude()
        else isValid = false
    }

    inline fun openingHours(openingHours: () -> Array<OpeningDay>) {
        this.`access$openingHours` = openingHours()
    }

    inline fun telephoneNumber(telephoneNumber: () -> String) {
        if (telephoneNumber().length >= MIN_LENGTH_PHONE_NUMBER)
            this.`access$telephoneNumber` = telephoneNumber()
    }

    inline fun webUrl(webUrl: () -> String) {
        if (webUrl().isvalidWebUrl())
            this.`access$webUrl` = webUrl()
    }

    inline fun img(img: () -> String) {
        this.`access$img` = img()
    }

    inline fun address(address: () -> String) {
        if (address().length > MIN_LENGTH_NAME)
            this.`access$address` = address()
    }

    fun build(): Either<Unit, Place> {
        return if (checkIsValid())
            Either.Right(Place(this))
        else
            Either.Left(Unit)
    }

    private fun checkIsValid(): Boolean {
        if (name.trim().isEmpty() || cityName.trim().isEmpty() || latitude.isNaN() || longitude.isNaN())
            isValid = false
        return isValid
    }

    @PublishedApi
    internal var `access$name`: String
        get() = name
        set(value) {
            name = value
        }

    @PublishedApi
    internal var `access$cityName`: String
        get() = cityName
        set(value) {
            cityName = value
        }

    @PublishedApi
    internal var `access$latitude`: Double
        get() = latitude
        set(value) {
            latitude = value
        }

    @PublishedApi
    internal var `access$longitude`: Double
        get() = longitude
        set(value) {
            longitude = value
        }

    @PublishedApi
    internal var `access$openingHours`: Array<OpeningDay>?
        get() = openingHours
        set(value) {
            openingHours = value
        }

    @PublishedApi
    internal var `access$telephoneNumber`: String?
        get() = telephoneNumber
        set(value) {
            telephoneNumber = value
        }

    @PublishedApi
    internal var `access$webUrl`: String?
        get() = webUrl
        set(value) {
            webUrl = value
        }

    @PublishedApi
    internal var `access$img`: String?
        get() = img
        set(value) {
            img = value
        }

    @PublishedApi
    internal var `access$address`: String?
        get() = address
        set(value) {
            address = value
        }
}