package com.socialevoeding.framework_androidsdk.remote.datasources

import com.socialevoeding.data.datasources.remote.NetworkConfig
import com.socialevoeding.data.datasources.remote.PlaceRemoteDataSource
import com.socialevoeding.data.dtos.remote.NetworkPlace
import com.socialevoeding.framework_androidsdk.remote.retrofit.creation.ServiceProviderImpl
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class PlaceRemoteDataSourceImpl(private val serviceProviderImpl: ServiceProviderImpl) : PlaceRemoteDataSource {
    override suspend fun getPlaces(
        queryString: String,
        currenPlaceName: String
    ): List<NetworkPlace> {
        val query = "${queryString.replace(' ', '+')}+$currenPlaceName"
        val url = NetworkConfig.BASE_URL_SEARCH + query
        val document: Document = Jsoup.connect(url).get()
        val redirectUrl = document.select(".cMjHbjVt9AZ__button").first().attr("href")
        val api = serviceProviderImpl.getPlacesService()
    //    val searchRedirectUrl = api.getPlacesSearchRedirectUrlAsync("voedselbank+gent").await()
    //    val placeDetailsUrl = api.getPlacesDetailsRedirectUrlAsync(searchRedirectUrl).await()
        val places = api.getPlacesAsync(redirectUrl)
        return api.getPlacesAsync(query).await()
    }
}