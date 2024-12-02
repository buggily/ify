package com.buggily.ify.data.gender

import com.buggily.core.remote.ApiResult
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.local.gender.LocalGenderSourceable
import com.buggily.ify.remote.gender.RemoteGender
import com.buggily.ify.remote.gender.RemoteGenderSourceable
import kotlinx.coroutines.flow.firstOrNull

internal class GenderRepository(
    private val remoteGenderSource: RemoteGenderSourceable,
    private val localGenderSource: LocalGenderSourceable,
) : GenderRepositable {

    override suspend fun getByName(name: String): DataResult<Gender> {
        localGenderSource.getByName(name).firstOrNull()?.let {
            return DataResult.Response.Local(it.to())
        }

        when (
            val result: ApiResult<RemoteGender, RemoteGender.Error> =
                remoteGenderSource.getByName(name)
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
        }.let { localGenderSource.insert(it.toLocal()) }

        localGenderSource.getByName(name).firstOrNull()?.let {
            return DataResult.Response.Remote(it.to())
        }

        return DataResult.Failure.Local
    }
}
