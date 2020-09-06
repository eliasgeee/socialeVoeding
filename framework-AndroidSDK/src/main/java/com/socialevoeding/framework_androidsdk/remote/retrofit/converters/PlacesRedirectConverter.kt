package com.socialevoeding.framework_androidsdk.remote.retrofit.converters

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

object PlacesRedirectConverter : Converter<ResponseBody, Flow<String>> {

    class PlacesRedirectFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type?,
            annotations: Array<Annotation?>?,
            retrofit: Retrofit?
        ): Converter<ResponseBody, *> {
            return PlacesRedirectConverter
        }
    }

    override fun convert(value: ResponseBody): Flow<String> {
        return flow{
            val resp = value.string()
            var url = ""
            try{
                url = Jsoup.parse(resp).select(".cMjHbjVt9AZ__button").first().attr("href")
                emit(url)
            }catch(e : Exception){}
        }
    }
}