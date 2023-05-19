package com.example.network

private const val SUCCESS ="Success"

sealed class ResponseWrapper<T>( val message:String?) {
    data class ApiError<T>(val code: Int, val error: String):ResponseWrapper<T>( error){}
    data class ApiSuccess<T>(val data: T):ResponseWrapper<T>(SUCCESS){}
}