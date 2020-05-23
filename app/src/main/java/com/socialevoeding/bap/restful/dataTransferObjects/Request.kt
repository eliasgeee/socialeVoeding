package com.socialevoeding.bap.restful.dataTransferObjects

data class Request(
    val processed_timestamp: Int? = null,
    val search_url: String? = null,
    val success: Boolean? = null,
    val total_time_taken: Double? = null
)

data class SearchInformation(
    val detected_location: DetectedLocation? = null,
    val did_you_mean: DidYouMean? = null,
    val no_results_for_original_query: Boolean? = null,
    val query_displayed: String? = null,
    val showing_results_for: ShowingResultsFor? = null,
    val time_taken_displayed: Double? = null,
    val total_results: Int? = null
)

data class SearchParameters(
    val access_key: String? = null,
    val action: String? = null,
    val auto_location: String? = null,
    val csv_fields: String? = null,
    val device: String? = null,
    val engine: String? = null,
    val error: Error? = null,
    val exclude_autocorrected_results: String? = null,
    val gl: String? = null,
    val google_domain: String? = null,
    val hl: String? = null,
    val images_color: String? = null,
    val news_type: String? = null,
    val num: String? = null,
    val output: String? = null,
    val page: String? = null,
    val query: String? = null,
    val safe: String? = null,
    val type: String? = null
)

class ShowingResultsFor(
)

data class Pagination(
    val current_page: Long? = null,
    val next_page_url: String? = null,
    val other_page_urls: OtherPageUrls? = null
)

data class OtherPageUrls(
    val `10`: String? = null,
    val `2`: String? = null,
    val `3`: String? = null,
    val `4`: String? = null,
    val `5`: String? = null,
    val `6`: String? = null,
    val `7`: String? = null,
    val `8`: String? = null,
    val `9`: String? = null
)

data class LocalMap(
    val coordinates: Coordinates? = null,
    val image_url: String? = null,
    val url: String? = null
)

class Error(
)

class DidYouMean(
)

class DetectedLocation(
)

class Price(
)

data class OrganicResult(
    val cached_page_url: Any? = null,
    val displayed_url: String? = null,
    val domain: String? = null,
    val position: Int? = null,
    val prerender: Boolean? = null,
    val related_pages_url: Any? = null,
    val snippet: String? = null,
    val title: String? = null,
    val url: String? = null
)