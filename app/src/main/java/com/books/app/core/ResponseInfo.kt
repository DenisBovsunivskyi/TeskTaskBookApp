package com.books.app.core

sealed class ResponseInfo <out T : Any,out U : Any> {
    data class Success <T: Any>(val data: T) : ResponseInfo<T, Nothing>()
    data class Error <U : Any>(val rawResponse: U) : ResponseInfo<Nothing, U>()
}