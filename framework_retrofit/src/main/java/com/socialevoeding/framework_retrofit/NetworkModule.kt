package com.socialevoeding.framework_retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.socialevoeding.data.datasources.remote.NetworkConfig
import com.socialevoeding.data.datasources.remote.UserLocationRemoteDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*al networkModule = module {
    single { provideRetrofit() }
    single { provideGeolocationApi() }
    single { RemoteReverseGeolocationSource(get()) as UserLocationRemoteDataSource}
    single { provideMoshi() }
}

fun provideGeolocationApi() : ReverseGeolocationApiService {
    return provideRetrofit().create(ReverseGeolocationApiService::class.java)
}

fun provideRetrofit() : Retrofit {
    return Retrofit.Builder()
        .baseUrl(NetworkConfig.BASE_URL_GEOLOCATION)
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()).asLenient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
*/