package com.buggily.ify.data.age

import com.buggily.core.remote.ApiResult
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.local.age.LocalAgeSourceable
import com.buggily.ify.remote.age.RemoteAge
import com.buggily.ify.remote.age.RemoteAgeSourceable
import kotlinx.coroutines.flow.firstOrNull

internal class AgeRepository(
    private val remoteAgeSource: RemoteAgeSourceable,
    private val localAgeSource: LocalAgeSourceable,
) : AgeRepositable {

    override suspend fun getByName(name: String): DataResult<Age> {
        localAgeSource.getByName(name).firstOrNull()?.let {
            return DataResult.Response.Local(it.to())
        }

        when (
            val result: ApiResult<RemoteAge, RemoteAge.Error> =
                remoteAgeSource.getByName(name)
        ) {
            is ApiResult.Response -> result.body
            is ApiResult.Failure.Api -> return DataResult.Failure.Remote.Api(
                message = result.fallbackBody.message,
            )
            is ApiResult.Failure.Network -> return DataResult.Failure.Remote.Network(
                message = result.exception.message,
            )
            is ApiResult.Failure.Else -> return DataResult.Failure.Remote.Else(
                message = result.throwable.message,
            )
        }.let { localAgeSource.insert(it.toLocal()) }

        localAgeSource.getByName(name).firstOrNull()?.let {
            return DataResult.Response.Remote(it.to())
        }

        return DataResult.Failure.Local
    }
}
