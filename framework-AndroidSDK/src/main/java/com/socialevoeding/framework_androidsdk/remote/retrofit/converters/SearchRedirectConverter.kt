package com.socialevoeding.framework_androidsdk.remote.retrofit.converters

import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object SearchRedirectConverter : Converter<ResponseBody, String> {

    class SearchRedirectFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type?,
            annotations: Array<Annotation?>?,
            retrofit: Retrofit?
        ): Converter<ResponseBody, *> {
            return SearchRedirectConverter
        }
    }

    override fun convert(value: ResponseBody): String? {
        val resp = value.string()

        val btn: Element = Jsoup.parse(resp).select(".eZt8xd").first()
        val url = btn.attr("href").substring(1)
        return url
    }
}