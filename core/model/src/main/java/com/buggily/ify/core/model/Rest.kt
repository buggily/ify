package com.buggily.ify.core.model

import java.io.IOException

sealed class Rest<out Body : Any, out ErrorBody : Any> {

    open val code: Code?
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
        override val code: Code,
        override val body: Body,
    ) : Rest<Body, Nothing>()

    sealed class Error<ErrorBody : Any> : Rest<Nothing, ErrorBody>() {

        class Api<ErrorBody : Any>(
            override val code: Code,
            val errorBody: ErrorBody,
        ) : Error<ErrorBody>()

        class Network(
            val exception: IOException,
        ) : Error<Nothing>()

        class Else(
            val throwable: Throwable,
        ) : Error<Nothing>()
    }

    enum class Code {

        Success,

        Unauthorized,
        PaymentRequired,
        UnprocessableEntity,
        TooManyRequests,

        Default;

        private val code: Int?
            get() = when (this) {
                Success -> 200

                Unauthorized -> 401
                PaymentRequired -> 402
                UnprocessableEntity -> 422
                TooManyRequests -> 429

                Default -> null
            }

        companion object {
            fun get(code: Int?): Code = values().find { it.code == code } ?: Default
        }
    }
}
