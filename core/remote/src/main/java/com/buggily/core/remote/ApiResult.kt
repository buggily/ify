package com.buggily.core.remote

import java.io.IOException

sealed interface ApiResult<out Body : Any, out FallbackBody : Any> {

    val code: HttpCode?
        get() = when (this) {
            is Response -> code
            is Failure.Api -> code
            else -> null
        }

    val body: Body?
        get() = when (this) {
            is Response -> body
            else -> null
        }

    class Response<Body : Any>(
        override val code: HttpCode,
        override val body: Body,
    ) : ApiResult<Body, Nothing>

    sealed interface Failure<FallbackBody : Any> : ApiResult<Nothing, FallbackBody> {

        class Api<FallbackBody : Any>(
            override val code: HttpCode,
            val fallbackBody: FallbackBody,
        ) : Failure<FallbackBody>

        class Network(
            val exception: IOException,
        ) : Failure<Nothing>

        class Else(
            val throwable: Throwable,
        ) : Failure<Nothing>
    }
}
