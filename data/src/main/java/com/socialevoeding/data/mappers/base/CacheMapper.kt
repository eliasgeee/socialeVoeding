package com.socialevoeding.data.mappers.base

interface CacheMapper<CacheItem, DomainObject> {
    fun mapToDomainObject(cacheItem: CacheItem): DomainObject
}