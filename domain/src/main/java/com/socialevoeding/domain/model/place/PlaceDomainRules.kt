package com.socialevoeding.domain.model.place

import java.util.regex.Pattern

const val MIN_LENGTH_NAME = 2
const val MIN_LENGTH_PHONE_NUMBER = 3

val WEB_URL_PATTERN: Pattern = Pattern
    .compile(".+\\..+")

fun String.isValidWebUrl(): Boolean {
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

fun String.isValidName(): Boolean {
    return this.trim().length >= MIN_LENGTH_NAME
}

fun String.isValidTelephoneNumber(): Boolean {
    return this.length >= MIN_LENGTH_PHONE_NUMBER
}