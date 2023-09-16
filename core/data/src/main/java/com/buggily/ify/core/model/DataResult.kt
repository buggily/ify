package com.buggily.ify.core.model

sealed interface DataResult<out Value> {

    sealed interface Response<Value> : DataResult<Value> {

        val value: Value

        data class Remote<Value>(
            override val value: Value,
        ) : Response<Value>

        data class Local<Value>(
            override val value: Value,
        ) : Response<Value>
    }

    sealed interface Failure : DataResult<Nothing> {

        data object Local : Failure

        sealed interface Remote : Failure {

            val message: String?

            data class Api(
                override val message: String,
            ) : Remote

            data class Network(
                override val message: String?,
            ) : Remote

            data class Else(
                override val message: String?,
            ) : Remote
        }
    }
}
