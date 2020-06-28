package com.socialevoeding.domain

import com.socialevoeding.domain.model.place.MIN_LENGTH_NAME
import com.socialevoeding.domain.model.place.MIN_LENGTH_PHONE_NUMBER
import com.socialevoeding.domain.model.place.buildPlace
import com.socialevoeding.util_datafactory.DataFactory
import com.socialevoeding.util_models.Either
import org.junit.Assert
import org.junit.Test

class PlaceTest {

    @Test
    fun `create place with all attributes and no errors passes`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(true, place.isRight)
    }

    @Test
    fun `create place with only required attributes and no errors passes`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
        }
        Assert.assertEquals(true, place.isRight)
    }

    @Test
    fun `create place without required attributes fails`() {
        val place = buildPlace {
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place without required attribute name fails`() {
        val place = buildPlace {
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }

        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place without required attribute cityName fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }

        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place without required attribute latitude fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }

        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place without required attribute longitude fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }

        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place with empty name fails`() {
        val place = buildPlace {
            name { "" }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place with empty name fails #2`() {
        val place = buildPlace {
            name { "      " }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place with empty cityName fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { "" }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place with empty cityName fails #2`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { "      " }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place with NaN latitude fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { Double.NaN }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place with NaN longitude fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { Double.NaN }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place with min length name passes`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(true, place.isRight)
    }

    @Test
    fun `create place with min length - 1 name fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME - 1) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `create place with min length cityName passes`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(true, place.isRight)
    }

    @Test
    fun `create place with min length - 1 cityName passes`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME - 1) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `short emergency number 112 passes telephoneNumber validation`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { "112" }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(true, place.isRight)
    }

    @Test
    fun `create place with wrong web url creates place with default url`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "ditdus" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(true, place.isRight)

        when (place) {
            is Either.Right -> Assert.assertEquals("", place.b.webUrl)
        }
    }

    @Test
    fun `create place with correct web url passes`() {
        val url = "facebook.com"
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { url }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(true, place.isRight)

        when (place) {
            is Either.Right -> Assert.assertEquals(url, place.b.webUrl)
        }
    }

    @Test
    fun `create place with correct web url passes #2`() {
        val url = "https://facebook.com"
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { url }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(true, place.isRight)

        when (place) {
            is Either.Right -> Assert.assertEquals(url, place.b.webUrl)
        }
    }

    @Test
    fun `create place with correct web url passes #3`() {
        val url = "https://www.facebook.com"
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { url }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(true, place.isRight)

        when (place) {
            is Either.Right -> Assert.assertEquals(url, place.b.webUrl)
        }
    }

    @Test
    fun `create place with correct web url passes #4`() {
        val url = "www.facebook.com"
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 51.0543 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { url }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(true, place.isRight)

        when (place) {
            is Either.Right -> Assert.assertEquals(url, place.b.webUrl)
        }
    }

    @Test
    fun `value above latitude range fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 1000000.0 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `value below latitude range fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { -100.0 }
            longitude { 3.7174 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `value above longitude range fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 50.0 }
            longitude { 181.0 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }

    @Test
    fun `value below longitude range fails`() {
        val place = buildPlace {
            name { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            cityName { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
            latitude { 50.0 }
            longitude { -181.0 }
            openingHours { emptyArray() }
            telephoneNumber { DataFactory.randomString(MIN_LENGTH_PHONE_NUMBER + 2) }
            webUrl { "www.ditdus.be" }
            address { DataFactory.randomString(MIN_LENGTH_NAME + 2) }
        }
        Assert.assertEquals(false, place.isRight)
    }
}