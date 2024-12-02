package com.buggily.ify.remote.nationality

internal class RemoteNationalitySource(
    private val remoteNationalityService: RemoteNationalityServiceable,
) : RemoteNationalitySourceable {

    override suspend fun getByName(
        name: String,
    ) = remoteNationalityService.getByName(
        name = name,
    )
}
