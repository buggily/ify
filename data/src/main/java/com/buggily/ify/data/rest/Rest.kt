package com.buggily.ify.data.rest

import java.io.IOException

sealed class Rest<out Body : Any, out ErrorBody : Any> {

    open val code: RestCode?
        get() = when (this) {
            is Success -> code
            is Error.Api -> code
            else -> null
        }

    open val body: Body?
        get() = when (this) {
            is Success -> body
            else -> null
        }

    class Success<Body : Any>(
        override val code: RestCode,
        override val body: Body,
    ) : Rest<Body, Nothing>()

    sealed class Error<ErrorBody : Any> : Rest<Nothing, ErrorBody>() {

        class Api<ErrorBody : Any>(
            override val code: RestCode,
            val errorBody: ErrorBody,
        ) : Error<ErrorBody>()

        class Network(
            val exception: IOException,
        ) : Error<Nothing>()

        class Else(
            val throwable: Throwable,
        ) : Error<Nothing>()
    }
}
