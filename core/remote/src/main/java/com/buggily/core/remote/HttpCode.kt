package com.buggily.core.remote

enum class HttpCode {

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
        fun get(code: Int?): HttpCode = values().find { it.code == code } ?: Default
    }
}
