package com.socialevoeding.framework_androidsdk.remote.retrofit.converters

import com.socialevoeding.framework_androidsdk.di.provideMoshi
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

@Retention(AnnotationRetention.RUNTIME)
annotation class SearchRedirect

@Retention(AnnotationRetention.RUNTIME)
annotation class Moshi

@Retention(AnnotationRetention.RUNTIME)
annotation class PlacesRedirect

object ConverterTypes {
    fun provideConverters() : AnnotatedConverterFactory{
        val converters = LinkedHashMap<String, Converter.Factory>()
        converters[Moshi::class.java.simpleName] = MoshiConverterFactory.create(
            provideMoshi()
        ).asLenient()
        converters[SearchRedirect::class.java.simpleName] = SearchRedirectConverter.SearchRedirectFactory()
        converters[PlacesRedirect::class.java.simpleName] = PlacesRedirectConverter.PlacesRedirectFactory()
        return AnnotatedConverterFactory(converters)
    }
}


