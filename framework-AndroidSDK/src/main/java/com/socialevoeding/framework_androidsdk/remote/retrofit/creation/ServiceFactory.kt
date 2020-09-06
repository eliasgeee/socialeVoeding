package com.socialevoeding.framework_androidsdk.remote.retrofit.creation

import com.socialevoeding.data.datasources.remote.Environment
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

class ServiceFactory(
    private val converterFactory: Converter.Factory,
    private val callAdapterFactory: CallAdapter.Factory,
    private val httpClient: OkHttpClient,
    private val environment: Environment
) {
    fun <T> create(serviceType: Class<T>): T {
        return create(
            serviceType,
            httpClient,
            converterFactory,
            callAdapterFactory,
            environment.baseUrl
        )
    }

    private fun getNetAdapter(
        client: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory,
        baseUrl: String
    ): Retrofit {
        val builder = Retrofit.Builder()
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .baseUrl(baseUrl)
        return builder.build()
    }

    private fun <T> create(
        serviceType: Class<T>,
        client: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory,
        baseUrl: String
    ): T {
        val retrofit = getNetAdapter(client, converterFactory, callAdapterFactory, baseUrl)
        return retrofit.create(serviceType)
    }
}