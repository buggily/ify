package com.buggily.ify.data.rest

enum class RestCode {

    // 200s

    Success,

    // 400s

    Unauthorized,
    PaymentRequired,
    UnprocessableEntity,
    TooManyRequests,

    // Default

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
        fun get(code: Int?): RestCode = values().find {
            it.code == code
        } ?: Default
    }
}
