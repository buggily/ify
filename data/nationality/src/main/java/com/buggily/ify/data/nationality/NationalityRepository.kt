package com.buggily.ify.data.nationality

import com.buggily.core.remote.ApiResult
import com.buggily.ify.core.model.DataResult
import com.buggily.ify.local.nationality.LocalNationalitySourceable
import com.buggily.ify.local.nationality.country.LocalNationalityCountrySourceable
import com.buggily.ify.remote.nationality.RemoteNationality
import com.buggily.ify.remote.nationality.RemoteNationalitySourceable
import kotlinx.coroutines.flow.firstOrNull

class NationalityRepository(
    private val remoteNationalitySource: RemoteNationalitySourceable,
    private val localNationalitySource: LocalNationalitySourceable,
    private val localNationalityCountrySource: LocalNationalityCountrySourceable
) : NationalityRepositable {

    override suspend fun getByName(name: String): DataResult<Nationality> {
        localNationalitySource.getByName(name).firstOrNull()?.let {
            return DataResult.Response.Local(it.to())
        }

        when (
            val result: ApiResult<RemoteNationality, RemoteNationality.Error> =
                remoteNationalitySource.getByName(name)
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
        }.let { nationality: RemoteNationality ->
            localNationalitySource.insert(nationality.toLocal())
            localNationalityCountrySource.insert(nationality.countries.map { it.toLocal(name) })
        }

        localNationalitySource.getByName(name).firstOrNull()?.let {
            return DataResult.Response.Remote(it.to())
        }

        return DataResult.Failure.Local
    }
}
