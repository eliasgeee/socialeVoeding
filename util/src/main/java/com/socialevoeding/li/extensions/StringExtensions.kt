package com.socialevoeding.li.extensions

fun String?.checkIsNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty() && !this.equals("null", ignoreCase = true)
}