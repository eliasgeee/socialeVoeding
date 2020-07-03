package com.socialevoeding.framework_androidsdk.remote.retrofit.converters

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class AnnotatedConverterFactory(
    private val factories : LinkedHashMap<String, Converter.Factory>
) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for(annotation in annotations) run {
            val factory: Converter.Factory? = factories[annotation.annotationClass.simpleName]

            factory?.let {
                return factory.responseBodyConverter(type, annotations, retrofit)
            }
        }
        return null
    }
}