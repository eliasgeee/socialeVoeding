package com.socialevoeding.framework_androidsdk.remote.retrofit.converters

import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object PlacesRedirectConverter : Converter<ResponseBody, String> {

    class PlacesRedirectFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type?,
            annotations: Array<Annotation?>?,
            retrofit: Retrofit?
        ): Converter<ResponseBody, *> {
            return PlacesRedirectConverter
        }
    }

    override fun convert(value: ResponseBody): String? {
        val url = Jsoup.parse(value.string()).select(".cMjHbjVt9AZ__button").first().attr("href")
        return url
    }
}