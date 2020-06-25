package com.socialevoeding.li.extensions

import java.util.*

fun String?.checkIsNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty() && !this.equals("null", ignoreCase = true)
}

fun String.getCategoryViewName() : String{
    return this.toUpperCase(Locale.getDefault())
}

fun String.getNormalizedName() : String{
    return this.trim().toUpperCase(Locale.getDefault())
}