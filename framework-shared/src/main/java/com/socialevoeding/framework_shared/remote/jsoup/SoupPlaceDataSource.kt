package com.socialevoeding.framework_shared.remote.jsoup

import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.dtos.remote.NetworkCoordinates
import com.socialevoeding.data.dtos.remote.NetworkOpeningDay
import com.socialevoeding.data.dtos.remote.NetworkPlace
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class SoupPlaceDataSource : PlaceRemoteDataSource {
    override suspend fun getPlaces(queryString: String, currenPlaceName: String): List<NetworkPlace> {
        return getPlacesSearch(queryString, currenPlaceName).map {
            getPlacesDetails(it)
            getPacesImages(it)
        }
    }

    private fun getPlacesSearch(queryString: String, currentPlaceName: String): List<NetworkPlace> {
        val url = SoupConfig.SOUP_BASE_URL_SEARCH + queryString.replace(' ', '+') + "+" + currentPlaceName
        val document: Document = Jsoup.connect(url).get()
        val btn: Element = document.select(".cMjHbjVt9AZ__button").first()
        val documentspec: Document = Jsoup.connect(SoupConfig.SOUP_BASE_URL.plus(btn.attr("href"))).get()
        val links = documentspec.select("div[jsname=GZq3Ke]")
        val places = ArrayList<NetworkPlace>(emptyList())

        for (link in links) {
            places.add(
                NetworkPlace(
                    name = link.select("div[class=dbg0pd] > div").text(),
                    networkCoordinates = NetworkCoordinates(
                        latitude = link.select("div[class=rllt__mi]").attr("data-lat").toDouble(),
                        longitude = link.select("div[class=rllt__mi]").attr("data-lng").toDouble()
                    ),
                    rating = link.select("span[class=sBhnyP5sXkG__rtng]").text().replace(',', '.')
                        .toFloat(),
                    url = link.select("a[class=yYlJEf L48Cpd]").attr("href")
                )
            )
        }

        return places
    }

    private fun getPlacesDetails(place: NetworkPlace): NetworkPlace {
            val urlPlace = SoupConfig.SOUP_BASE_URL_SEARCH + place.name.replace(
                ' ',
                '+'
            ) + "+" + place.name
            val documentPlace: Document = Jsoup.connect(urlPlace).get()

            place.address = documentPlace.select("span[class=LrzXr]").text()
            place.telephoneNumber = documentPlace.select("span[LrzXr zdqRlf kno-fv]  a").text()

            val hours = documentPlace.select("table[class=WgFkxc] tr")

            hours.forEach {
                place.openingHours.add(
                    NetworkOpeningDay(
                        it.select("tr > td").first().text(),
                        it.select("td:nth-child(2)").text()
                    )
                )
        }

        return place
    }

    private fun getPacesImages(place: NetworkPlace): NetworkPlace {
        val imgUrl = "http://google.com/search?tbm=isch&q=${place.name}+${place.name}"

        val documentImg: Document = Jsoup.connect(imgUrl).get()

        val scripts = documentImg.select("script")
        var imgsrchtml =
            scripts.first { element -> element.html().startsWith("_setImgSrc('0',", 0, true) }
                .html()
        imgsrchtml = imgsrchtml.replace("\\", "")
        imgsrchtml = imgsrchtml.substring(16, imgsrchtml.length - 3)
        val chars = imgsrchtml.toCharArray()
        val charIndex = chars.indexOf(',')
        imgsrchtml = imgsrchtml.substring(charIndex + 1)
        // imgsrchtml = imgsrchtml.replace(Base64Regex.baseRegex, "")
        place.img_url = imgsrchtml
        return place
    }
}