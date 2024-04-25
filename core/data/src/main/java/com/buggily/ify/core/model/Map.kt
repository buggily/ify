package com.buggily.ify.core.model

fun <T, S> DataResult<T>.toBy(to: (T) -> S): DataResult<S> = when (this) {
    is DataResult.Response.Remote -> DataResult.Response.Remote(to(value))
    is DataResult.Response.Local -> DataResult.Response.Local(to(value))

    is DataResult.Failure.Local -> DataResult.Failure.Local
    is DataResult.Failure.Remote.Api -> DataResult.Failure.Remote.Api(message)
    is DataResult.Failure.Remote.Network -> DataResult.Failure.Remote.Network(message)
    is DataResult.Failure.Remote.Else -> DataResult.Failure.Remote.Else(message)
}