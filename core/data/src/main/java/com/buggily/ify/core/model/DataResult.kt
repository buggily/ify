package com.buggily.ify.core.model

sealed class DataResult<out Value> {

    sealed class Response<Value>(
        open val value: Value
    ) : DataResult<Value>() {

        data class Remote<Value>(
            override val value: Value,
        ) : Response<Value>(
            value = value,
        )

        data class Local<Value>(
            override val value: Value,
        ) : Response<Value>(
            value = value,
        )
    }

    sealed class Failure : DataResult<Nothing>() {

        object Local : Failure()

        sealed class Remote : Failure() {

            abstract val message: String?

            data class Api(
                override val message: String,
            ) : Remote()

            data class Network(
                override val message: String?,
            ) : Remote()

            data class Else(
                override val message: String?,
            ) : Remote()
        }
    }
}
