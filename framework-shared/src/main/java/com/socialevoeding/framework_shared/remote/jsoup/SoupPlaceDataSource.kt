package com.socialevoeding.framework_shared.remote.jsoup

import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.dtos.remote.NetworkCoordinates
import com.socialevoeding.data.dtos.remote.NetworkOpeningDay
import com.socialevoeding.data.dtos.remote.NetworkPlace
import kotlinx.coroutines.flow.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import sun.nio.ch.Net
import java.lang.Exception

class SoupPlaceDataSource : PlaceRemoteDataSource {

    override suspend fun getPlaces(
        queryString: String,
        currentPlaceName: String
    ): Flow<List<NetworkPlace>> {
        return flow {
            val redirectUrl = getRedirectUrl(queryString, currentPlaceName)
            val elements = getPlacesElements(redirectUrl)
            val places = ArrayList<NetworkPlace>()
            for (element in elements){
                places.add(getNetWorkPlace(element).getPlaceDetails().getImage())
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

    private fun getPlacesElements(url : String): List<Element> {
           val documentspec: Document = Jsoup.connect(SoupConfig.SOUP_BASE_URL + url).timeout(120 * 1000).get()
           return documentspec.select("div[jsname=GZq3Ke]")
    }

    private fun getNetWorkPlace(link : Element) : NetworkPlace{
        var name = ""
        try {
            name = link.select("div[class=dbg0pd] > div").text()
        }catch(e : Exception){}
        var networkCoordinates = NetworkCoordinates()
        try {
            networkCoordinates = NetworkCoordinates(
                latitude = link.select("div[class=rllt__mi]").attr("data-lat").toDouble(),
                longitude = link.select("div[class=rllt__mi]").attr("data-lng").toDouble()
            )
        }catch(e : Exception){}
        var rating = 0F
        try {
            rating = link.select("span[class=sBhnyP5sXkG__rtng]").text().replace(',', '.')
                .toFloat()
        }catch(e : Exception){}
        var weburl = ""
        try {
            weburl = link.select("a[class=yYlJEf L48Cpd]").attr("href")
        }catch(e : Exception){}
        return NetworkPlace(
                name = name,
                networkCoordinates = networkCoordinates,
                rating = rating,
                url = weburl
            )
    }

    private fun NetworkPlace.getPlaceDetails() : NetworkPlace {
                try{
                    val urlPlace = SoupConfig.SOUP_BASE_URL_SEARCH + this.name.replace(
                        ' ',
                        '+'
                    ) + "+" + this.name
                    val documentPlace: Document = Jsoup.connect(urlPlace).get()

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
                }
                catch (e : Exception){}
        return this
    }

    private fun NetworkPlace.getImage(): NetworkPlace {
                try {
                    val imgUrl = "http://google.com/search?tbm=isch&q=${this.name}"

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
                    this.img_url = imgsrchtml
                }
                catch (e : Exception){}
        return this
    }
}