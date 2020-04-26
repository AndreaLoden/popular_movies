package com.jetbrains.handson.mpp.mobile

sealed class ApiError
data class UnknownError(val code: Int) : ApiError()
object NetworkError : ApiError()
object ItemNotFoundError : ApiError()
