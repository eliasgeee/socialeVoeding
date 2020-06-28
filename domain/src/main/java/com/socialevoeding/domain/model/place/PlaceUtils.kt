package com.socialevoeding.domain.model.place

import java.util.regex.Pattern

val WEB_URL_PATTERN: Pattern = Pattern
    .compile(".+\\..+")

fun String.isvalidWebUrl(): Boolean {
    return WEB_URL_PATTERN.matcher(this).matches()
}

val LATITUDE_PATTERN: Pattern = Pattern.compile("[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)")

fun Double.isValidLatitude(): Boolean {
    return LATITUDE_PATTERN.matcher(this.toString()).matches()
}
val LONGITUDE_PATTERN: Pattern = Pattern.compile("\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)")

fun Double.isValidateLongitude(): Boolean {
    return LONGITUDE_PATTERN.matcher(this.toString()).matches()
}