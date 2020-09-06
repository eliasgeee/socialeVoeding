package com.socialevoeding.presentation_android.mappers.base

interface ViewItemMapper<ViewItem, DomainObject> {
    fun mapToViewItem(model: DomainObject): ViewItem
}