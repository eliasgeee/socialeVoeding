package com.socialevoeding.framework_androidsdk.remote.retrofit.converters

import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object PlacesConverter : Converter<ResponseBody, String> {

    class PlacesFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type?,
            annotations: Array<Annotation?>?,
            retrofit: Retrofit?
        ): Converter<ResponseBody, *> {
            return PlacesConverter
        }
    }

    override fun convert(value: ResponseBody): String? {
        val response = value.string()
        return ""
    }
}