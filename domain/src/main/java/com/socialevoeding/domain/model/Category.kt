package com.socialevoeding.domain.model

import java.io.Serializable

sealed class Category(
    val id: Int,
    val name: String
)
