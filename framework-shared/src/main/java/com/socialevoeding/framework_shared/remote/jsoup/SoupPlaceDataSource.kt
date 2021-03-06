package com.socialevoeding.framework_shared.remote.jsoup

import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.dtos.remote.NetworkCoordinates
import com.socialevoeding.data.dtos.remote.NetworkOpeningDay
import com.socialevoeding.data.dtos.remote.NetworkPlace
import kotlinx.coroutines.flow.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class SoupPlaceDataSource : PlaceRemoteDataSource {

    override suspend fun getPlaces(
        queryString: String,
        currentPlaceName: String
    ): Flow<List<NetworkPlace>> {
        return flow {
            val redirectUrl = getRedirectUrl(queryString, currentPlaceName)
            val elements = getPlacesElements(redirectUrl)
            val places = ArrayList<NetworkPlace>()
            for (element in elements) {
                val placeToAdd = getNetWorkPlace(element)
                    .setCity(currentPlaceName)
                places.add(placeToAdd)
                emit(places)
                placeToAdd.getPlaceDetails()
                places.add(placeToAdd)
                emit(places)
                placeToAdd.getImage()
                places.add(placeToAdd)
                emit(places)
            }
        }
    }

    private fun getRedirectUrl(queryString: String, currentPlaceName: String): String {
        val url = SoupConfig.SOUP_BASE_URL_SEARCH + queryString.replace(' ', '+') + "+" + currentPlaceName
        val document: Document = Jsoup.connect(SoupConfig.SOUP_BASE_URL_SEARCH + "voedselbank gent").timeout(60 * 1000).get()
        val btn: Element = document.select(".cMjHbjVt9AZ__button").first()
        return btn.attr("href")
    }

    private fun getPlacesElements(url: String): List<Element> {
        val documentspec: Document = Jsoup.connect(SoupConfig.SOUP_BASE_URL + url).timeout(120 * 1000).get()
        return documentspec.select("div[jsname=GZq3Ke]")
    }

    private fun getNetWorkPlace(link: Element): NetworkPlace {
        var name = ""
            name = link.select("div[class=dbg0pd] > div").text()
        val networkCoordinates = NetworkCoordinates(
                latitude = link.select("div[class=rllt__mi]").attr("data-lat").toDouble(),
                longitude = link.select("div[class=rllt__mi]").attr("data-lng").toDouble()
        )
        val rating = link.select("span[class=sBhnyP5sXkG__rtng]").text().replace(',', '.')
                .toFloat()
        val weburl = link.select("a[class=yYlJEf L48Cpd]").attr("href")
        return NetworkPlace(
                name = name,
                networkCoordinates = networkCoordinates,
                rating = rating,
                url = weburl
            )
    }

    private fun NetworkPlace.getPlaceDetails(): NetworkPlace {
                    /*val urlPlace = SoupConfig.SOUP_BASE_URL_SEARCH + this.name.replace(
                        ' ',
                        '+'
                    ) + "+" + this.name*/
                    val documentPlace: Document = Jsoup.connect(SoupConfig.SOUP_BASE_URL_SEARCH + this.name).timeout(120 * 1000).get()

                    this.address = documentPlace.select("span[class=LrzXr]").text()
                    this.telephoneNumber = documentPlace.select("span[LrzXr zdqRlf kno-fv]  a").text()

                    val hours = documentPlace.select("table[class=WgFkxc] tr")

                    hours.forEach {
                        this.openingHours.add(
                            NetworkOpeningDay(
                                it.select("tr > td").first().text(),
                                it.select("td:nth-child(2)").text()
                            )
                        )
                    }
        return this
    }

    private fun NetworkPlace.getImage(): NetworkPlace {
                    val imgUrl = "http://google.com/search?tbm=isch&q=${this.name}"
                    val documentImg: Document = Jsoup.connect(imgUrl).timeout(120 * 1000).get()

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
                    this.img_url = imgsrchtml
        return this
    }

    fun NetworkPlace.setCity(city: String): NetworkPlace {
        cityName = city
        return this }
}