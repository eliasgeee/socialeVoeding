package com.socialevoeding.bap.usecases.base

data class ErrorModel(
    val message: String?,
    val code: Int?,
    @Transient var errorStatus: ErrorStatus
) {
    constructor(errorStatus: ErrorStatus) : this(null, null, errorStatus)
}