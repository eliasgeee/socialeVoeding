package com.socialevoeding.data.utils

import java.util.*

fun String.toPrimaryKey() : String {
    this.apply {
        trim()
        toUpperCase(Locale.getDefault())
    }
    return this
}